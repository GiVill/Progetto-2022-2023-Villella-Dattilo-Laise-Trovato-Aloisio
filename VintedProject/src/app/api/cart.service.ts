import { Injectable } from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class CartService {


  constructor( private cookieService: CookieService,
               private snackBar: MatSnackBar,
               private httpClient: HttpClient) { }



  addToCart(insertionId: number | undefined): void {
    if (insertionId !== undefined) {
      let cartItems: { insertion_id: number }[] = [];
      const cartItemsCookie = this.cookieService.get('cartItems');
      if (cartItemsCookie) {
        cartItems = JSON.parse(cartItemsCookie) as { insertion_id: number }[];
      }
      const existingProductIndex = cartItems.findIndex(item => item.insertion_id === insertionId);
      if (existingProductIndex !== -1) {
        this.snackBar.open("Prodotto gia nel carrello" , "OK")
      } else {
        cartItems.push({ insertion_id: insertionId });
      }
      this.cookieService.set('cartItems', JSON.stringify(cartItems), 1, '/');
    } else {
      this.snackBar.open("Inserzione non disponibile" , "RIPROVA");
    }
  }





}
