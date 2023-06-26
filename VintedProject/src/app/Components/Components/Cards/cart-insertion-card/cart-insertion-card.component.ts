import {Component, Input} from '@angular/core';

import {Router} from "@angular/router";
import {BasicInsertion} from "../../../../Model/basic-insertion.model";
import {CartComponent} from "../../../Pages/cart/cart.component";


@Component({
  selector: 'app-cart-insertion-card',
  templateUrl: './cart-insertion-card.component.html',
  styleUrls: ['./cart-insertion-card.component.css']
})
export class CartInsertionCardComponent {
  @Input() item: BasicInsertion | undefined;

  isHovered = false;

  constructor(private router: Router,
              private cart: CartComponent) {
  }

  onImageClick(): void {
    this.router.navigate(['/insertion', this.item?.id]);
  }

  remove(insertionId: number | undefined): void {
    if (insertionId) {
      this.cart.removeFromCart(insertionId);
    }
  }
}
