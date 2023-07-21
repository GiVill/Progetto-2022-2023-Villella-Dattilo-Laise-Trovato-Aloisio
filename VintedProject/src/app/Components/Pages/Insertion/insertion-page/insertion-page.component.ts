import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {switchMap} from "rxjs";
import {CookieService} from "ngx-cookie-service";
import {InsertionService} from "../../../../service/insertion.service";
import {CartService} from "../../../../service/cart.service";
import {UserService} from "../../../../service/user.service";
import {OrderService} from "../../../../service/order.service";
import {BasicInsertionDto} from "../../../../Model/basicInsertionDto";
import {PageBasicInsertionDto} from "../../../../Model/pageBasicInsertionDto";
import {UserDto} from "../../../../Model/userDto";


@Component({
  selector: 'app-insertion-page',
  templateUrl: './insertion-page.component.html',
  styleUrls: ['./insertion-page.component.css']
})

export class InsertionPageComponent implements OnInit {
  users: UserDto[] = [];
  insertion: BasicInsertionDto | undefined;
  user: UserDto | undefined;
  page = 0;
  // @ts-ignore
  userOtherInsertion!: PageBasicInsertionDto;
  id: number | undefined;
  modalOpen = false;
  modalImage: string | undefined;
  isProductInCart=false;

  constructor(
    private insertionService: InsertionService,
    private route: ActivatedRoute,
    private cookieService: CookieService,
    private cartService: CartService,
    private userService: UserService,
    private orderService: OrderService,) {
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


}

