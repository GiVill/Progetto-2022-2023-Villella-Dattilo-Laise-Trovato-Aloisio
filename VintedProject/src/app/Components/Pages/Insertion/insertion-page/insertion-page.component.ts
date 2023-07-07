import {Component, OnInit} from '@angular/core';

import {ActivatedRoute} from "@angular/router";

import {Observable, switchMap} from "rxjs";

import {CookieService} from "ngx-cookie-service";
import {BasicInsertionDto} from "../../../../Model/basicInsertionDto";
import {UserDto} from "../../../../Model/userDto";
import {InsertionService} from "../../../../service/insertion.service";
import {CartService} from "../../../../service/cart.service";
import {UserService} from "../../../../service/user.service";
import {ImageService} from "../../../../service/image.service";
import {PageBasicInsertionDto} from "../../../../Model/pageBasicInsertionDto";


@Component({
  selector: 'app-insertion-page',
  templateUrl: './insertion-page.component.html',
  styleUrls: ['./insertion-page.component.css']
})

export class InsertionPageComponent implements OnInit {
  insertion: BasicInsertionDto | undefined;
  user: UserDto | undefined;
  page = 1;
  // @ts-ignore
  userOtherInsertion: PageBasicInsertionDto | undefined;
  id: number | undefined;
  modalOpen = false;
  modalImage: string | undefined;
  isProductInCart=false;

  constructor(
    private insertionService: InsertionService,
    private route: ActivatedRoute,
    private cookieService: CookieService,
    private cartService: CartService,
    private userService: UserService
  ) {
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
            this.userService.getUserById(this.insertion.userId).subscribe(
              (userData: UserDto) => {
                this.user = userData;
                console.log(this.user?.id); // Verifica qui
              },
              (error) => {
                console.log('Si è verificato un errore durante il recupero dell\'utente:', error);
              }
            );

            this.userService.getAllInsertionsByUser(this.id, this.page).subscribe(
              (data: PageBasicInsertionDto) => {
                this.userOtherInsertion = data;
              },
              (error) => {
                console.log('Si è verificato un errore durante il recupero delle altre inserzioni dell\'utente:', error);
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


  async processImages(): Promise<void> {
    if (this.insertion) {
      const imagePath = await ImageService.setProductImageSrc(this.insertion.image);
      if (imagePath) {
        this.insertion = {...this.insertion, imagePath};
      }
    }
  }

  openModal(imageSrc: string | undefined): void {
    this.modalOpen = true;
    this.modalImage = imageSrc;
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
}
