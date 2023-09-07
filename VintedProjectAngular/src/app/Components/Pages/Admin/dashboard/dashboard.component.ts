import { Component } from '@angular/core';
import {UserDto} from "../../../../model/userDto";
import {PageOrderDto} from "../../../../model/pageOrderDto";
import {PaymentDto} from "../../../../model/paymentDto";
import {PageBasicInsertionDto} from "../../../../model/pageBasicInsertionDto";
import {OrderService} from "../../../../api/order.service";
import {InsertionService} from "../../../../api/insertion.service";
import {UserService} from "../../../../api/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {OfferService} from "../../../../api/offer.service";
import {PageBuyngOfferDto} from "../../../../model/pageBuyngOfferDto";
import {BuyingOfferDto} from "../../../../model/buyingOfferDto";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  insertionId!: number;
  userId!: number ;
  orderId!: number;
  offerId!: number;
  InsertionId!: number;
  paymentId!: number | undefined;
  page=0;
  activeButton: string = 'getAllByUserId';
  userDtoArray: UserDto[] = [];
  orderDtoArray!: PageOrderDto;
  offerDtoArray!: BuyingOfferDto[]
  paymentDtoArray: PaymentDto[] = [];
  insertionDtoArray!: PageBasicInsertionDto
  userEmail!: string;



  constructor(private orderService : OrderService,
              private insertionService :InsertionService,
              private offerService :OfferService,
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


  getAllOffer() {
    this.activeButton = 'getAllOffer';
    // Clear other arrays when a different action is selected
    this.clearDataArrays();
  }

  deleteOffer() {
    this.activeButton = 'deleteOffer';
    // Clear other arrays when a different action is selected
    this.clearDataArrays();
  }

  actionGetAllByUserId() {
    if (this.userId){
      this.insertionService.getInsertionByUserId(this.userId, this.page).subscribe(
        (insertions: PageBasicInsertionDto) => {
          this.insertionDtoArray = { ...insertions };
        },
        (error) => {
          this.snackBar.open('Errore nel recupero delle inserzioni', 'RIPROVA');
        }
      );
    }else{
      this.insertionService.getInsertionByUserEmail(this.userEmail, this.page).subscribe(
        (insertions: PageBasicInsertionDto) => {
          this.insertionDtoArray = { ...insertions };
        },
        (error) => {
          this.snackBar.open('Errore nel recupero delle inserzioni', 'RIPROVA');

        }
      );
    }
  }

  actionDeleteBasicInsertion(InsertionId) {
    this.insertionService.adminDeleteInsertionByInsertionId(InsertionId).subscribe(
      response => {
        this.actionGetAllByUserId()
        this.snackBar.open('Inserzione Eliminata', 'OK');
      },
      error => {
        this.snackBar.open('Non è stato possibile eliminare questa inserzione', 'OK');
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
        }
      );
    }else{
      this.orderService.getOrderDtoByIdByEmailPagedAdmin(this.userEmail, this.page).subscribe(
        (order: PageOrderDto) => {
          this.orderDtoArray = order;
        },
        (error) => {
          this.snackBar.open('Non è stato possibile recuperare gli ordini', 'OK');
        }
      );
    }
  }

  actionDeleteOrder(orderId) {
   this.orderService.deleteOrderById(orderId).subscribe(
     response => {
       this.snackBar.open('Ordine Eliminato', 'OK');

     },
     error => {
       this.snackBar.open('Non è stato possibile eliminare questo ordine', 'OK');
     }
   );
  }

  actionDeleteOffer(offerId) {
    this.offerService.adminDeleteOffer(offerId).subscribe(
      response => {
        this.snackBar.open('Offerta Eliminata', 'OK');
      },
      error => {
        this.snackBar.open('Non è stato possibile eliminare questa offerta', 'OK');
      }
    );
  }

  actionGetOfferByUserId(){
    if (this.userId) {
      this.offerService.getAllByUserId(this.userId).subscribe(
        (offer: BuyingOfferDto[]) => {
          this.offerDtoArray = offer;
        },
        (error) => {
          this.snackBar.open('Non è stato possibile recuperare le offerte', 'OK');
        }
      );
    }else{
      this.offerService.adminGetAllByInsertionId(this.insertionId).subscribe(
        (order: BuyingOfferDto[]) => {
          this.offerDtoArray = order;
        },
        (error) => {
          this.snackBar.open('Non è stato possibile recuperare gli ordini', 'OK');
        }
      );
    }
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
      case 'getAllOffer':
        this.actionGetOfferByUserId();
        break;
      case 'deleteOffer':
        this.actionDeleteOffer(this.offerId);
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


