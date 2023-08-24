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
import {PaymentService} from "../../../api/payment.service";
import {Observable} from "rxjs";
import {OrderDto} from "../../../model/orderDto";
import {CookiesService} from "../../../api/cookies.service";


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {


  product: any[] = [];
  cartProduct?: BasicInsertionDto[];
  selectedPaymentMethod: string | undefined;
  paymentMethods: PaymentDto.PaymentMethodEnum[] = Object.values(PaymentDto.PaymentMethodEnum);
  ordineCreato = false;
  totalCost = 0;
  orderSuccess = false;
  orderError = false;
  areProduct: Boolean = false;
  loadedProducts: BasicInsertionDto[] = [];




  constructor(
    private cookieService: CookieService,
    private insertionService: InsertionService,
    private cartService: CartService,
    private http: HttpClient,
    private router: Router,
    private orderService: OrderService,
    private tokenService: TokenService,
    private cookieServices: CookiesService,
  ) {}

  ngOnInit(): void {
    const cartItemsCookie = this.cookieService.get('cartItems');
    if (cartItemsCookie) {
      this.product = JSON.parse(cartItemsCookie);
      if (this.product.length>0){
        this.areProduct=true;
      }
    } else {
      this.product = [];
    }
    this.loadProductDetails();
    console.log(this.product);
    console.log(this.cookieService.get('userId'));
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


  private saveCartItems(cartItems: { insertion_id: number}[]): void {
    this.cookieService.delete('cartItems', '/');
    const updatedCartItems = cartItems.map(item => ({ insertion_id: item.insertion_id}));
    this.cookieService.set('cartItems', JSON.stringify(updatedCartItems), 1, '/');
  }

  createOrder(): void {
    if (this.product.length === 0) {
      console.log("Nessun prodotto nel carrello. Impossibile creare l'ordine.");
      return;
    }
    const cartItemsCookie = this.cookieService.get('cartItems');
    const order: OrderDto = {
      id: 0,
      payment_method: this.selectedPaymentMethod!,
      insertionIdList: this.loadedProducts,
      userId: Number(this.cookieServices.getUserId()),
      total: this.totalCost
    };
    console.log("prodotti" , this.loadedProducts)
    const token = this.cookieService.get('jwtToken');
    this.orderService.addOrder(order).subscribe(
        response => {
          console.log("Ordine creato con successo:", response);
          this.ordineCreato = true;
          this.orderSuccess = true;
          this.orderError = false;
          // TODO: Assegnare a questo pagamento l'id dell'ordine
          this.clearCart();
        },
        error => {
          console.log("Errore durante la creazione dell'ordine:", error);
          this.ordineCreato = false;
          this.orderSuccess = false;
          this.orderError = true;
        }

    );
    console.log(order)
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
