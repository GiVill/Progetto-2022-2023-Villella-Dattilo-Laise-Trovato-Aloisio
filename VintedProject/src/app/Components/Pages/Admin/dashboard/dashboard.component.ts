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
import {Observable} from "rxjs";
import {PageableObject} from "../../../../Model/pageableObject";
import {SortObject} from "../../../../Model/sortObject";
import {PageOrderDto} from "../../../../Model/pageOrderDto";
import {MatSnackBar, SimpleSnackBar} from "@angular/material/snack-bar";

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
  activeButton: string = 'getAllByUserId';
  userDtoArray: UserDto[] = [];
  orderDtoArray!: PageOrderDto;
  paymentDtoArray: PaymentDto[] = [];
  insertionDtoArray!: PageBasicInsertionDto



  constructor(private orderService : OrderService,
              private insertionService :InsertionService,
              private userService :UserService,
              private snackBar: MatSnackBar,
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

  searchGetAllByUserId() {
    console.log(this.userId);
    this.insertionService.getInsertionByUserId(this.userId, this.page).subscribe(
      (insertions: PageBasicInsertionDto) => {
        this.insertionDtoArray = { ...insertions };
        console.log(this.insertionDtoArray);
      },
      (error) => {
        console.log("Error", error);
      }
    );
  }
  searchDeleteBasicInsertion(InsertionId) {
    this.insertionService.deleteInsertionForAdmin(InsertionId).subscribe(
      response => {
        this.searchGetAllByUserId()
        this.snackBar.open('Inserzione Eliminata', 'OK');

        console.log("Inserzione Eliminata", response);
      },
      error => {
        this.snackBar.open('Non è stato possibile eliminare questa inserzione', 'OK');

        console.log("Errore", error);
      }
    );
  }



  searchGetOrderUserById() {
    this.orderService.getUserOrders(this.userId, this.page).subscribe(
      (order: PageOrderDto) => {
        this.orderDtoArray = order;
      },
      (error) => {
        this.snackBar.open('Non è stato possibile eliminare questo ordine', 'OK');

        console.log("Error", error);
      }
    );
  }

  searchDeleteOrder(orderId) {
   this.orderService.deleteOrderForAdmin(orderId).subscribe(
     response => {
       console.log("Ordine Eliminato", response);

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
        this.searchDeleteBasicInsertion(this.insertionId);
        break;
      case 'getAllByUserId':
        this.searchGetAllByUserId();
        break;
      case 'getOrderById':
        this.searchGetOrderUserById();
        break;
      case 'deleteOrder':
        this.searchDeleteOrder(this.orderId);
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
   // this.orderDtoArray = [];
    this.paymentDtoArray = [];
    //this.insertionDtoArray= [];
  }
}


