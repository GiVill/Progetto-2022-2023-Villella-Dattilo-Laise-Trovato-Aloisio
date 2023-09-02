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
  OrderModal: boolean= false;

  constructor() {
  }

  onImageClick(): void {
    this.OrderModal=true;
  }

  closeOrderModal(event: any) {
    if (event.target.classList.contains('orders-modal')) {
      this.openOrderModal()
    }
  }

    openOrderModal() {
      this.OrderModal = !this.OrderModal;
    }



}
