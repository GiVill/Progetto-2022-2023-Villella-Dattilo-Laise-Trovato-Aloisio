import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {switchMap} from "rxjs";
import {CookieService} from "ngx-cookie-service";
import {InsertionService} from "../../../../api/insertion.service";
import {CartService} from "../../../../api/cart.service";
import {UserService} from "../../../../api/user.service";
import {BasicInsertionDto} from "../../../../model/basicInsertionDto";
import {PageBasicInsertionDto} from "../../../../model/pageBasicInsertionDto";
import {UserDto} from "../../../../model/userDto";
import {CookiesService} from "../../../../api/cookies.service";
import {ErrorService} from "../../../../api/error.service";
import {BuyingOfferDto} from "../../../../model/buyingOfferDto";
import {MatSnackBar} from "@angular/material/snack-bar";
import {OfferService} from "../../../../api/offer.service";
import {ChatMessageService} from "../../../../api/chatMessage.service";
import {NewChatDto} from "../../../../model/newChatDto";


@Component({
  selector: 'app-insertion-page',
  templateUrl: './insertion-page.component.html',
  styleUrls: ['./insertion-page.component.css']
})

export class InsertionPageComponent implements OnInit {
  users: UserDto[] = [];
  insertion?: BasicInsertionDto;
  user: UserDto | undefined;
  page = 0;
  userOtherInsertion!: PageBasicInsertionDto;
  id: number | undefined;
  modalOpen = false;
  modalImage: string | undefined;
  isProductInCart=false;
  isMyProduct=false;
  offerModalOpen = false;
  offerAmount: number | undefined;
  messageModalOpen:boolean  = false;
  message: string = "";
  constructor(
    private insertionService: InsertionService,
    private route: ActivatedRoute,
    private cookieService: CookieService,
    private cookiesService: CookiesService,
    private cartService: CartService,
    private buyngOffer: OfferService,
    private userService: UserService,
    private error: ErrorService,
    private router: Router,
    private chatMessageService: ChatMessageService,
   private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.route.paramMap
      .pipe(
        switchMap((params) => {
          this.id = Number(params.get('id'));
          return this.insertionService.getInsertionById(this.id);
        })
      )
      .subscribe(
        (data: BasicInsertionDto) => {
          this.insertion = data;
          if (this.insertion?.userId) {
            this.userService.getUserDtoById(this.insertion.userId).subscribe(
              (userData: UserDto) => {
                this.user = userData;
                if (Number(this.user?.id) == Number(this.cookiesService.getUserId())){
                  this.isMyProduct=true;
                }
                this.insertionService.getInsertionByUserId(Number(this.user.id), this.page).subscribe(
                  (data: PageBasicInsertionDto) => {
                    this.userOtherInsertion = data;
                    const userIds = this.userOtherInsertion.content!.map((insertion) => insertion.userId).filter((id, index, array) => array.indexOf(id) === index);
                    userIds.forEach((userId) => {
                      this.userService.getUserDtoById(userId).subscribe((user: UserDto) => {
                        this.users.push(user);
                      });
                    });
                  },
                  (error) => {
                    this.snackBar.open("Si è verificato un errore durante il recupero delle inserzioni dell\'utente.")
                  }
                );
              },
              (error) => {
                this.snackBar.open("Si è verificato un errore durante il recupero delle informazioni dell\'utente.")
              }
            );

          }
        },
        (error) => {
          if (!this.error.redirectToErrorPage(error)) {
            this.error.redirectToErrorPage(error)
          }
          this.snackBar.open("Si è verificato un errore durante il recupero dell' inserzione.")
        }

      );

    this.checkProductInCart();
  }

  openModal(): void {
    this.modalOpen = true;
    this.modalImage = this.insertion?.imageName;
  }

  closeModal(): void {
    this.modalOpen = false;
    this.modalImage = undefined;
    this.closeOfferModal()

  }

  addToCart() {
      if (this.id !== undefined) {
        this.cartService.addToCart(this?.id);
      }
    this.checkProductInCart();
  }

checkProductInCart(): void {
  if (this.id !== undefined) {
  const cartItemsCookie = this.cookieService.get('cartItems');
  if (cartItemsCookie) {
    const cartItems: { insertion_id: number }[] = JSON.parse(cartItemsCookie);
    const productInCart = cartItems.some(item => item.insertion_id === this.id);
    if (productInCart) {
      this.isProductInCart=true;
    }
  }}}

  getUserByUserId(userId: number): UserDto  {
    return <UserDto>this.users.find(user => user.id === userId);
  }

  chat(){
    this.router.navigate(['/newchat', this.user?.id]);
  }

  closeModalOnClick(event: any): void {
    if (event.target.classList.contains('offer-message-modal')) {
      this.closeOfferModal();
      this.closeMessageModal();
    }
  }

  openOfferModal(): void {
    this.offerModalOpen = true;
  }

  closeOfferModal(): void {
    this.offerModalOpen = false;
    this.offerAmount = undefined;
  }

  openMessageModal() {
    this.messageModalOpen=true;
  }

  closeMessageModal() {
    this.messageModalOpen=false;
  }


  submitOffer(): void {
    if (this.offerAmount !== undefined && this.offerAmount > 0) {
      const buyingOffer: BuyingOfferDto = {
        id: 0,
        price: this.offerAmount,
        status: "PENDING",
        insertionId: this.insertion?.id!,
        userId: Number(this.cookiesService.getUserId()),
      };
      this.buyngOffer.userAddBuyingOffer(buyingOffer).subscribe(
        (response) => {
          this.snackBar.open("Offerta inviata con successo. Puoi verificare lo stato nel tuo profilo")
          this.closeOfferModal();
        },
        (error) => {
          this.snackBar.open("Errore nell'invio dell'offerta")
        }
      );
    } else {
      this.snackBar.open("l'offerta deve essere maggiore di 0")
    }
  }

  submitMessage() {
    if (this.message.trim() !== '') {
      const newChatDto: NewChatDto = {
        sender: Number(this.cookiesService.getUserId()),
        reciver: this.user?.id,
        message: this.message,
        insertionId: Number(this.insertion?.id),
        user1NameLastname: (this.cookiesService.getUserId()),
        user2NameLastname: (this.user?.firstName + " " + this.user?.lastName),
        insertionTitle: this.insertion?.title,
      };
      this.chatMessageService.newChat(newChatDto).subscribe(

        (response: string) => {
            this.snackBar.open("Messaggio inviato", "OK")
            this.closeMessageModal()
        },
        (error) => {
          if (error.statusText=="OK") {
            this.snackBar.open("Messaggio inviato" , "OK")
            this.closeMessageModal()
          }else {
            this.snackBar.open("Errore nell'invio del messaggio" , "RIPROVA")
            this.closeModal()
          }
        }
      );
    }
  }


}

