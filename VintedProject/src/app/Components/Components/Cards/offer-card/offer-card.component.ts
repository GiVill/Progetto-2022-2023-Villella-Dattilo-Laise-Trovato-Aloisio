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


@Component({
  selector: 'app-offer-card',
  templateUrl: './offer-card.component.html',
  styleUrls: ['./offer-card.component.css'],
})
export class OfferCardComponent implements OnInit{
  @Input() item?: BuyingOfferDto;
  product?: BasicInsertionDto;
  isHovered = false;
  accepted: boolean = false;


  constructor(private router: Router,
              private basicInsertion: InsertionService,
              private offerService: OfferService,
              private orderService: OrderService,
              private cookieServices: CookiesService,
              private snackBar: MatSnackBar,
             ) {
  }

  onImageClick(): void {
    this.router.navigate(['/Order', this.item?.id]);
  }

  ngOnInit(): void {
    this.basicInsertion.getInsertionById(this.item?.insertionId).subscribe((data: BasicInsertionDto) => {
        this.product = data;
        if (this.item?.status == "APPROVED")
          this.accepted=true;
      },
      (error) => {
        console.error('Error fetching product:', error);
      }
    );{

    }
  }


  deleteOffer() {
    this.offerService._delete(Number(this.item?.id)).subscribe((response) => {
      this.snackBar.open("Offerta eliminata", )
      },
      (error) => {
        this.snackBar.open("Errore nell'eliminazione dell offerta")
        console.error('Error fetching product:', error);
      }
    );{

    }

  }

  createOrder() {

    if (this.product?.id != undefined) {
      const order: OrderDto = {
        id: 0,
        paymentId: 0,
        date: new Date().getDate().toString(),
        insertionIdList: [this.product?.id],
        userId: Number(this.cookieServices.getUserId())
      };
      this.orderService.addOrder(order).subscribe((response) => {
          this.snackBar.open("Ordine creato ",)
        },
        (error) => {
          this.snackBar.open("Errore durante la creazione dell ordine")
        }
      );
      {

      }
    }
    this.snackBar.open("Errore durante la creazione dell ordine")
  }
}
