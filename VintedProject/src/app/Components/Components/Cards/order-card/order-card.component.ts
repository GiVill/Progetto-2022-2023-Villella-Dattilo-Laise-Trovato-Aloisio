import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";
import {CartComponent} from "../../../Pages/cart/cart.component";
import {OrderDto} from "../../../../Model/orderDto";

@Component({
  selector: 'app-order-card',
  templateUrl: './order-card.component.html',
  styleUrls: ['./order-card.component.css']
})
export class OrderCardComponent {
  @Input() item: OrderDto | undefined;

  isHovered = false;

  constructor(private router: Router,
              private cart: CartComponent) {
  }

  onImageClick(): void {
    this.router.navigate(['/Order', this.item?.id]);
  }

  remove(insertionId: number | undefined): void {
    if (insertionId) {
      this.cart.removeFromCart(insertionId);
    }
  }
}
