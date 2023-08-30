import { Component } from '@angular/core';
import {UserDto} from "../../../../model/userDto";
import {PageOrderDto} from "../../../../model/pageOrderDto";
import {PaymentDto} from "../../../../model/paymentDto";
import {PageBasicInsertionDto} from "../../../../model/pageBasicInsertionDto";
import {OrderService} from "../../../../api/order.service";
import {InsertionService} from "../../../../api/insertion.service";
import {UserService} from "../../../../api/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";

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
  userEmail!: string;



  constructor(private orderService : OrderService,
              private insertionService :InsertionService,
              private userService :UserService,
              private snackBar: MatSnackBar,
              ) {
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

  actionGetAllByUserId() {
    console.log(this.userId);
    if (this.userId){
      this.insertionService.getInsertionByUserId(this.userId, this.page).subscribe(
        (insertions: PageBasicInsertionDto) => {
          this.insertionDtoArray = { ...insertions };
          console.log(this.insertionDtoArray);
        },
        (error) => {
          console.log("Error", error);
        }
      );
    }else{
      console.log(this.userEmail);
      this.insertionService.getInsertionByUserEmail(this.userEmail, this.page).subscribe(
        (insertions: PageBasicInsertionDto) => {
          this.insertionDtoArray = { ...insertions };
          console.log(this.insertionDtoArray);
        },
        (error) => {
          console.log("Error", error);
        }
      );
    }
  }

  actionDeleteBasicInsertion(InsertionId) {
    this.insertionService.adminDeleteInsertionByInsertionId(InsertionId).subscribe(
      response => {
        this.actionGetAllByUserId()
        this.snackBar.open('Inserzione Eliminata', 'OK');

        console.log("Inserzione Eliminata", response);
      },
      error => {
        this.snackBar.open('Non è stato possibile eliminare questa inserzione', 'OK');

        console.log("Errore", error);
      }
    );
  }



  actionGetOrderUserById() {
    if (this.userId) {
      this.orderService.getOrderDtoByIdPagedAdmin(this.userId, this.page).subscribe(
        (order: PageOrderDto) => {
          this.orderDtoArray = order;
        },
        (error) => {
          this.snackBar.open('Non è stato possibile recuperare gli ordini', 'OK');

          console.log("Error", error);
        }
      );
    }else{
      this.orderService.getOrderDtoByIdByEmailPagedAdmin(this.userEmail, this.page).subscribe(
        (order: PageOrderDto) => {
          this.orderDtoArray = order;
        },
        (error) => {
          this.snackBar.open('Non è stato possibile recuperare gli ordini', 'OK');

          console.log("Error", error);
        }
      );
    }
  }

  actionDeleteOrder(orderId) {
   this.orderService.deleteOrderById(orderId).subscribe(
     response => {
       this.snackBar.open('Ordine Eliminato', 'OK');
       console.log("Ordine Eliminato", response);

     },
     error => {
       this.snackBar.open('Non è stato possibile eliminare questo ordine', 'OK');
       console.log("Errore", error);
     }
   );
  }

  searchFindAll() {

  }

  actionDeletePayment() {

  }

  action() {
    switch (this.activeButton) {
      case 'deleteBasicInsertion':
        this.actionDeleteBasicInsertion(this.insertionId);
        break;
      case 'getAllByUserId':
        this.actionGetAllByUserId();
        break;
      case 'getOrderById':
        this.actionGetOrderUserById();
        break;
      case 'deleteOrder':
        this.actionDeleteOrder(this.orderId);
        break;
      case 'findAll':
        this.searchFindAll();
        break;
      case 'deletePayment':
        this.actionDeletePayment();
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


