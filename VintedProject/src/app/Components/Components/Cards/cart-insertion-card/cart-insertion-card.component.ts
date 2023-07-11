import {Component, Input} from '@angular/core';

import {Router} from "@angular/router";

import {CartComponent} from "../../../Pages/cart/cart.component";
import {BasicInsertionDto} from "../../../../model/basicInsertionDto";


@Component({
  selector: 'app-cart-insertion-card',
  templateUrl: './cart-insertion-card.component.html',
  styleUrls: ['./cart-insertion-card.component.css']
})
export class CartInsertionCardComponent {
  @Input() item: BasicInsertionDto | undefined;

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
