import {Component, OnInit} from '@angular/core';
import {BasicInsertion} from "../../Model/basic-insertion.model";
import {InsertionService} from "../../service/insertion.service";
import {Page} from "../../Model/page.model";
import {ImageService} from "../../service/image.service";
import {ActivatedRoute} from "@angular/router";
import {User} from "../../Model/user.model";
import {UserService} from "../../service/user.service";
import {Observable, switchMap} from "rxjs";
import {CartService} from "../../service/cart.service";
import {CookieService} from "ngx-cookie-service";


@Component({
  selector: 'app-insertion-page',
  templateUrl: './insertion-page.component.html',
  styleUrls: ['./insertion-page.component.css']
})

export class InsertionPageComponent implements OnInit {
  insertion: BasicInsertion | undefined;
  user: User | undefined;
  page = 1;
  userOtherInsertion: Page<BasicInsertion> | undefined;
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
        (data: BasicInsertion) => {
          this.insertion = data;
          console.log(this.insertion);
          if (this.insertion?.userId) {
            this.userService.getUserById(this.insertion.userId).subscribe(
              (userData: User) => {
                this.user = userData;
                console.log(this.user?.id); // Verifica qui
              },
              (error) => {
                console.log('Si è verificato un errore durante il recupero dell\'utente:', error);
              }
            );

            this.userService.getAllInsertionsByUser(this.id, this.page).subscribe(
              (data: Page<BasicInsertion>) => {
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
      const imageSrc = await ImageService.setProductImageSrc(this.insertion.image);
      if (imageSrc) {
        this.insertion = {...this.insertion, imageSrc};
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

