import {Component, Input, OnInit} from '@angular/core';
import {OrderDto} from "../../../../model/orderDto";
import {Router} from "@angular/router";
import {CartComponent} from "../../../Pages/cart/cart.component";
import {BuyingOfferDto} from "../../../../model/buyingOfferDto";
import {BasicInsertionDto} from "../../../../model/basicInsertionDto";
import {InsertionService} from "../../../../api/insertion.service";
import {ChatDto} from "../../../../model/chatDto";
import {OfferService} from "../../../../api/offer.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {OrderService} from "../../../../api/order.service";
import {CookiesService} from "../../../../api/cookies.service";
import {PaymentDto} from "../../../../model/paymentDto";
import {MyprofileComponent} from "../../../Pages/User/myprofile/myprofile.component";
import {getLocaleDateFormat} from "@angular/common";


@Component({
  selector: 'app-offer-card',
  templateUrl: './offer-card.component.html',
  styleUrls: ['./offer-card.component.css'],
})
export class OfferCardComponent implements OnInit{
  @Input() offer?: BuyingOfferDto;
  @Input() product?: BasicInsertionDto;
  isHovered = false;
  accepted: boolean = false;
  refused: boolean = false;


  constructor(private offerService: OfferService,
              private myProfile: MyprofileComponent,
              private orderService: OrderService,
              private cookieServices: CookiesService,
              private snackBar: MatSnackBar,
             ) {
  }


  ngOnInit(): void {
        if (this.offer?.status == "APPROVED")
          this.accepted=true;
        if (this.offer?.status == "REFUSED")
        this.refused=true;
}




  deleteOffer() {
    this.offerService.userDeleteOffer(this.offer!).subscribe((response) => {
      this.snackBar.open("Offerta eliminata", "OK")
      this.myProfile.getUserOffer();
      },
      (error) => {
        this.snackBar.open("Errore nell'eliminazione dell offerta" , "RIPROVA")
      }
    );{
    }
  }

  createOrder() {
    if (this.product?.id != undefined && this.accepted) {
      const order: OrderDto = {
        id: 0,
        total: this.offer?.price,
        paymentMethod: "CARD",
        insertionIdList: [this.product.id],
        userId: Number(this.cookieServices.getUserId())
      };
      this.orderService.userAddOfferOrder(order, this.offer?.id!).subscribe((response) => {
          this.snackBar.open("Ordine creato ",)
        this.myProfile.getUserOffer()
          this.myProfile.getUserOrders()
        },
        (error) => {
          this.snackBar.open("Errore durante la creazione dell ordine" , "RIPROVA")
        }
      );
      {

      }
    }
    this.snackBar.open("Errore durante la creazione dell ordine")
  }
}
