import { Component } from '@angular/core';
import {UserDto} from "../../../../Model/userDto";
import {PaymentDto} from "../../../../Model/paymentDto";
import {OrderDto} from "../../../../Model/orderDto";
import {BasicInsertionDto} from "../../../../Model/basicInsertionDto";
import {OrderService} from "../../../../service/order.service";
import {InsertionService} from "../../../../service/insertion.service";
import {PageBasicInsertionDto} from "../../../../Model/pageBasicInsertionDto";
import {UserService} from "../../../../service/user.service";
import {PaymentService} from "../../../../service/payment.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  insertionId!: number;
  userId!: number ;
  orderId!: number;
  paymentId!: number | undefined;
  page=0;
  activeButton: string = 'deleteBasicInsertion';
  userDtoArray: UserDto[] = [];
  orderDtoArray: OrderDto[] = [];
  paymentDtoArray: PaymentDto[] = [];
  insertionDtoArray: BasicInsertionDto[] = [];

  constructor(private orderService : OrderService,
              private insertionService :InsertionService,
              private userService :UserService,
              private paymentService :PaymentService) {
  }


  addPage(){
    this.page+=1;
  }

  subPage(){
    this.page-=1;
  }

  deleteBasicInsertion() {
    this.activeButton = 'deleteBasicInsertion';
    // Clear other arrays when a different action is selected
    this.clearDataArrays();
  }

  getAllByUserId() {
    this.activeButton = 'getAllByUserId';
    // Clear other arrays when a different action is selected
    this.clearDataArrays();
  }

  getOrderById() {
    this.activeButton = 'getOrderById';
    // Clear other arrays when a different action is selected
    this.clearDataArrays();
  }

  deleteOrder() {
    this.activeButton = 'deleteOrder';
    // Clear other arrays when a different action is selected
    this.clearDataArrays();
  }

  findAll() {
    this.activeButton = 'findAll';
    // Clear other arrays when a different action is selected
    this.clearDataArrays();
  }

  deletePayment() {
    this.activeButton = 'deletePayment';
    // Clear other arrays when a different action is selected
    this.clearDataArrays();
  }

  searchDeleteBasicInsertion() {
      this.insertionService.deleteInsertionForAdmin(this.orderId).subscribe(
        response => {
          console.log("Inserzione Eliminata", response);
        },
        error => {
          console.log("Errore", error);
        }
      );


  }

  searchGetAllByUserId() {
   this.insertionService.getInsertionByUserId(this.userId, this.page)
  }


  searchGetOrderById() {
    this.orderService.getOrderByIdAdmin(this.orderId).subscribe(
      (order: OrderDto) => {
        this.orderDtoArray = [order];
      },
      error => {
        console.log("Errore", error);
      }
    );
  }

  searchDeleteOrder() {
   this.orderService.deleteOrderForAdmin(this.orderId).subscribe(
     response => {
       console.log("Ordine Eliminato", response);
     },
     error => {
       console.log("Errore", error);
     }
   );
  }

  searchFindAll() {

  }

  searchDeletePayment() {
    this.paymentService.deletePaymentAdmin(this.orderId).subscribe(
      response => {
        console.log("Pagamento Eliminato", response);
      },
      error => {
        console.log("Errore", error);
      }
    );
  }

  search() {
    switch (this.activeButton) {
      case 'deleteBasicInsertion':
        this.searchDeleteBasicInsertion();
        break;
      case 'getAllByUserId':
        this.searchGetAllByUserId();
        break;
      case 'getOrderById':
        this.searchGetOrderById();
        break;
      case 'deleteOrder':
        this.searchDeleteOrder();
        break;
      case 'findAll':
        this.searchFindAll();
        break;
      case 'deletePayment':
        this.searchDeletePayment();
        break;
      default:
        break;
    }
  }

  private clearDataArrays() {
    this.userDtoArray = [];
    this.orderDtoArray = [];
    this.paymentDtoArray = [];
    this.insertionDtoArray = [];
  }
}


