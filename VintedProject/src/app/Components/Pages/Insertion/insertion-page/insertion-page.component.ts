import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {switchMap} from "rxjs";
import {CookieService} from "ngx-cookie-service";
import {InsertionService} from "../../../../api/insertion.service";
import {CartService} from "../../../../api/cart.service";
import {UserService} from "../../../../api/user.service";
import {OrderService} from "../../../../api/order.service";
import {BasicInsertionDto} from "../../../../model/basicInsertionDto";
import {PageBasicInsertionDto} from "../../../../model/pageBasicInsertionDto";
import {UserDto} from "../../../../model/userDto";
import {CookiesService} from "../../../../api/cookies.service";
import {ErrorService} from "../../../../api/error.service";
import {BuyingOfferService} from "../../../../api/buying-offer.service";
import {BuyingOfferDto} from "../../../../model/buyingOfferDto";
import {MatSnackBar} from "@angular/material/snack-bar";
import {OfferService} from "../../../../api/offer.service";
import {NewMessageDto} from "../../../../model/newMessageDto";
import {ChatService} from "../../../../api/chat.service";


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
    private chatService: ChatService,
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
          console.log(this.insertion);
          if (this.insertion?.userId) {
            this.userService.getById(this.insertion.userId).subscribe(
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
                      this.userService.getById(userId).subscribe((user: UserDto) => {
                        this.users.push(user);
                      });
                    });
                  },
                  (error) => {

                    console.log('Si è verificato un errore durante il recupero delle altre inserzioni dell\'utente:', error);
                  }
                );
              },
              (error) => {
                console.log('Si è verificato un errore durante il recupero dell\'utente:', error);
              }
            );

          }
        },
        (error) => {
          if (!this.error.redirectToErrorPage(error)) {
            this.error.redirectToErrorPage(error)
          }
          console.log('Si è verificato un errore durante il recupero dell\'inserzione:', error);
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
      console.log(this.isProductInCart)
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
        insertionId: this.insertion?.id,
        userId: Number(this.cookiesService.getUserId()),
      };
      console.log(buyingOffer)
      this.buyngOffer.addBuyingOffer(buyingOffer).subscribe(
        (response) => {
          this.snackBar.open("Offerta inviata con successo. Puoi verificare lo stato nel tuo profilo")

          console.log('Offerta inviata con successo:', response);
          this.closeOfferModal();
        },
        (error) => {
          console.error('Errore durante l\'invio dell\'offerta:', error);
          this.snackBar.open("Errore nell'invio dell'offerta")
        }
      );
    } else {
      console.error('L\'importo dell\'offerta deve essere maggiore di 0');
      this.snackBar.open("l'offerta deve essere maggiore di 0")
    }
  }

  submitMessage() {
    if (this.message.trim() !== '') {
      const newMessageDto: NewMessageDto = {
        sender: Number(this.cookiesService.getUserId()),
        reciver: this.user?.id,
        nickname: this.user?.nickname,
        message: this.message
      };
      console.log(newMessageDto)
      this.chatService.insertMessage(newMessageDto).subscribe(

        (response: string) => {
          console.log(response)
            this.snackBar.open("Messaggio inviato")
            this.closeMessageModal()
        },
        (error) => {
          if (error.statusText=="OK") {
            this.snackBar.open("Messaggio inviato")
            this.closeMessageModal()
          }else {
            this.snackBar.open("Errore nell'invio del messaggio")
            this.closeModal()
          }
        }
      );
    }
  }


}

