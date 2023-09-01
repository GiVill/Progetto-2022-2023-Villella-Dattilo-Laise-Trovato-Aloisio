import {Component, Input} from '@angular/core';
import {OrderDto} from "../../../../model/orderDto";
import {Router} from "@angular/router";
import {CartComponent} from "../../../Pages/cart/cart.component";


@Component({
  selector: 'app-order-card',
  templateUrl: './order-card.component.html',
  styleUrls: ['./order-card.component.css']
})
export class OrderCardComponent {
  @Input() order!: OrderDto;

  isHovered = false;

  constructor(private router: Router,) {
  }

  onImageClick(): void {
    this.router.navigate(['/Order', this.order]);
  }

  remove(insertionId: number | undefined): void {

  }
}
