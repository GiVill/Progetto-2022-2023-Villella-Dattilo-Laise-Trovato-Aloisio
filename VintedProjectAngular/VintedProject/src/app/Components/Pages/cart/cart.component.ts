import {Component, OnInit} from "@angular/core";
import {BasicInsertionDto} from "../../../model/basicInsertionDto";
import {PaymentDto} from "../../../model/paymentDto";
import {CookieService} from "ngx-cookie-service";
import {InsertionService} from "../../../api/insertion.service";
import {CartService} from "../../../api/cart.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {OrderService} from "../../../api/order.service";
import {TokenService} from "../../../api/token.service";
import {Observable} from "rxjs";
import {OrderDto} from "../../../model/orderDto";
import {CookiesService} from "../../../api/cookies.service";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {


  product: any[] = [];
  cartProduct?: BasicInsertionDto[];
  selectedPaymentMethod: string = "CARD";
  paymentMethods: PaymentDto.PaymentMethodEnum[] = Object.values(PaymentDto.PaymentMethodEnum);
  ordineCreato = false;
  totalCost = 0;
  orderSuccess = false;
  orderError = false;
  areProduct: Boolean = false;
  loadedProducts: BasicInsertionDto[] = [];
  order?: OrderDto;


  constructor(
    private cookieService: CookieService,
    private insertionService: InsertionService,
    private cartService: CartService,
    private http: HttpClient,
    private router: Router,
    private orderService: OrderService,
    private cookieServices: CookiesService,
    private snackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    const cartItemsCookie = this.cookieService.get('cartItems');
    if (cartItemsCookie) {
      this.product = JSON.parse(cartItemsCookie);
      if (this.product.length > 0) {
        this.areProduct = true;
      }
    } else {
      this.product = [];
    }
    this.loadProductDetails();
  }

  loadProductDetails(): void {
    let productRequests: Observable<BasicInsertionDto>[] = this.product.map((cartItem) =>
      this.insertionService.getInsertionById(cartItem.insertion_id)
    );


    for (const request of productRequests) {
      request.subscribe(
        (data: BasicInsertionDto) => {
          this.loadedProducts.push(data); // Add the loaded product to the array
          const productPrice = data.price ?? 0; // Replace `item.price` with the actual property name from BasicInsertionDto
          this.totalCost += productPrice;

          // Check if all products have been loaded
          if (this.loadedProducts.length === productRequests.length) {
            this.cartProduct = this.loadedProducts; // Assign the array of loaded products to cartProduct
          }
        },
        () => {
          this.snackBar.open("Errore durante il recupero delle inserzioni. Un prodotto potrebbe non essere più disponibile." , "RIPROVA")
        }
      );
    }
  }

  removeFromCart(insertionId: number): void {
    const index = this.product.findIndex(item => item.insertion_id === insertionId);
    if (index !== -1) {
      this.product.splice(index, 1);
      this.saveCartItems(this.product);
      this.removeProductFromCart(insertionId);
    }
  }

  removeProductFromCart(insertionId: number): void {
    if (this.cartProduct) {
      const index = this.cartProduct.findIndex(item => item.id === insertionId);
      if (index !== -1) {
        this.cartProduct.splice(index, 1);
      }
    }
    this.insertionService.getInsertionById(Number(insertionId)).subscribe((data: BasicInsertionDto) => {
      const productPrice = data.price ?? 0;
      this.totalCost -= productPrice;
    });

  }


  private saveCartItems(cartItems: { insertion_id: number }[]): void {
    this.cookieService.delete('cartItems', '/');
    const updatedCartItems = cartItems.map(item => ({insertion_id: item.insertion_id}));
    this.cookieService.set('cartItems', JSON.stringify(updatedCartItems), 1, '/');
  }

  createOrder(): void {
    if (this.product.length === 0) {
      this.snackBar.open("Nessun prodotto nel carrello..." , "OK")
      return;
    }

    this.order = {
      id: 0,
      total: this.totalCost,
      paymentMethod: this.selectedPaymentMethod,
      insertionIdList:this.getInsertionId(),
      userId: Number(this.cookieServices.getUserId())
    }


    this.orderService.userAddOrder(this.order!).subscribe((response) => {
        this.snackBar.open("Ordine creato, controlla nel tuo profilo.", "OK")
        this.cookieService.delete('cartItems', '/');
        this.clearCart()
      },
      (error) => {
        this.snackBar.open("Errore durante la creazione dell ordine." , "RIPROVA")
      }
    );
    {
      this.snackBar.open("Errore durante la creazione dell ordine." , "RIPROVA")
    }


  }

 getInsertionId(){
   let lista: number[] = [];  // Inizializza l'array vuoto qui
   this.product.forEach((cartItem) => {
     lista.push(cartItem.insertion_id);
   });
   return lista;
 }


  clearCart(): void {
    // Clear the cart items and update the cookie
    this.product = [];
    this.saveCartItems(this.product);

    // Clear the loaded products in cartProduct
    this.cartProduct = undefined;

    // Recalculate the total cost
    this.totalCost=0;
  }


}
