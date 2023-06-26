import {Component, OnInit} from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {forkJoin, Observable} from "rxjs";
import {BasicInsertion} from "../../../Model/basic-insertion.model";
import {InsertionService} from "../../../service/insertion.service";
import {CartService} from "../../../service/cart.service";


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  product: any[] = [];
  cartProduct: BasicInsertion[] | undefined;
  ordineCreato = false;
  logStringResultBool = false;
  totalCost = 0;
  orderSuccess = false;
  orderError = false;

  constructor(
    private cookieService: CookieService,
    private insertionService: InsertionService,
    private cartService: CartService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    const cartItemsCookie = this.cookieService.get('cartItems');
    if (cartItemsCookie) {
      this.product = JSON.parse(cartItemsCookie);
    } else {
      this.product = [];
    }
    this.loadProductDetails();
    console.log(this.product);
  }

  loadProductDetails(): void {
    const productRequests: Observable<BasicInsertion>[] = this.product.map((cartItem) =>
      this.insertionService.getInsertionById(cartItem.insertion_id)
    );

    const loadedProducts: BasicInsertion[] = []; // Array to store loaded products

    for (const request of productRequests) {
      request.subscribe(
        (data: BasicInsertion) => {
          loadedProducts.push(data); // Add the loaded product to the array
          console.log(data);
          this.calculateTotalCost();

          // Check if all products have been loaded
          if (loadedProducts.length === productRequests.length) {
            this.cartProduct = loadedProducts; // Assign the array of loaded products to cartProduct
          }
        },
        (error) => {
          console.log('Si è verificato un errore durante il recupero delle altre inserzioni dell\'utente:', error);
        }
      );
    }
  }

  removeFromCart(insertionId: number): void {
    const index = this.product.findIndex(item => item.insertion_id === insertionId);
    if (index !== -1) {
      this.product.splice(index, 1);
      this.saveCartItems(this.product);
      this.calculateTotalCost();
      this.removeProductFromCartProduct(insertionId);
    }
  }

  removeProductFromCartProduct(insertionId: number): void {
    if (this.cartProduct) {
      const index = this.cartProduct.findIndex(item => item.id === insertionId);
      if (index !== -1) {
        this.cartProduct.splice(index, 1);
      }
    }
  }



  private saveCartItems(cartItems: { insertion_id: number}[]): void {
    this.cookieService.delete('cartItems', '/');
    const updatedCartItems = cartItems.map(item => ({ insertion_id: item.insertion_id}));
    this.cookieService.set('cartItems', JSON.stringify(updatedCartItems), 1, '/');
  }
  calculateTotalCost(): void {
    let totalCost = 0;

    for (const item of this.product) {
      const productPrice = item.product?.prezzo ?? 0;
      const quantity = item.quantity;
      totalCost += productPrice * quantity;
    }

    this.totalCost = totalCost;
  }
}
