import { Injectable } from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CartService {


  constructor( private cookieService: CookieService,
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
        console.log("Prodotto già nel carrello");
      } else {
        cartItems.push({ insertion_id: insertionId });
      }
      this.cookieService.set('cartItems', JSON.stringify(cartItems), 1, '/');
    } else {
      console.log("ID di inserzione non definito");
    }
  }





}
