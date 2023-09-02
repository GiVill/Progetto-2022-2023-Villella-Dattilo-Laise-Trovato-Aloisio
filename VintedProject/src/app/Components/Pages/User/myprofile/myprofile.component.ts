import {Component,OnInit} from '@angular/core';
import {InsertionService} from "../../../../api/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../../../api/user.service";
import {forkJoin, switchMap} from "rxjs";
import {OrderService} from "../../../../api/order.service";
import {CookiesService} from "../../../../api/cookies.service";
import {PageBasicInsertionDto} from "../../../../model/pageBasicInsertionDto";
import {PageOrderDto} from "../../../../model/pageOrderDto";
import {UserDto} from "../../../../model/userDto";
import {BuyingOfferDto} from "../../../../model/buyingOfferDto";
import {OfferService} from "../../../../api/offer.service";



@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.css']
})
export class MyprofileComponent implements OnInit{
  user: UserDto | undefined;
  myInsertion!: PageBasicInsertionDto;
  myOrder!: PageOrderDto;
  myOffer!:Array<BuyingOfferDto>;
  page = 0;
  isAnyInsertion = false;
  isAnyOrder = false;
  isAnyOffer = false;
  userId = Number(this.cookieSevices.getUserId());
  showUpdateSectionFlag = false;
  InsertionModal=false
  OfferModal = false
  OrderModal = false



  constructor(
    private insertionService: InsertionService,
    private route: ActivatedRoute,
    private userService: UserService,
    private orderService: OrderService,
    private offerService: OfferService,
    private cookieSevices: CookiesService,
  ) {}

  ngOnInit(): void {
    if (!this.cookieSevices.checkUserToken()){
      this.cookieSevices.getRefreshToken()
    }
    this.route.paramMap.pipe(
      switchMap((params) => {
        return this.userService.getUserDtoById(this.userId);
      })
    ).subscribe(
      (user: UserDto) => {
        this.user = user;
        // Create observables for each API call
        const insertionObservable = this.insertionService.getMyInsertion(this.page);
        const offerObservable = this.offerService.getAllByUser();
        const orderObservable = this.orderService.getUserOrders(this.page);
        // Combine observables using forkJoin
        forkJoin([
          insertionObservable,
          offerObservable,
          orderObservable
        ]).subscribe(
          ([insertionData, offerData, orderData]) => {
            this.myInsertion = insertionData;
            this.myOffer = offerData;
            this.myOrder = orderData ;
            console.log("ordine" + orderData.content)

            if (!this.myInsertion?.empty) {
              this.isAnyInsertion = true;
            }
            if (!this.myOrder.empty) {
              this.isAnyOrder = true;
            }
            if (this.myOffer) {
              this.isAnyOffer = true;
            }

            console.log('All data retrieved:', this.myInsertion, this.myOffer, this.myOrder);
          },
          (error) => {
            console.log('An error occurred:', error);
          }
        );
      }
    );
  }

  updateOffer(){
      const offerObservable = this.offerService.getAllByUser();
    forkJoin([
      offerObservable,
    ]).subscribe(([offerData]) => {
        this.myOffer = offerData;

        if (this.myInsertion?.empty) {
            this.isAnyInsertion = true;
        }

        if (this.myOrder?.empty) {
            this.isAnyOrder = true;
        }

        console.log('All data retrieved:', this.myInsertion, this.myOffer, this.myOrder);
    }, (error) => {
        console.log('An error occurred:', error);
    });
  }




    getUserOrders(): void {
        this.orderService.getUserOrders(this.page).subscribe(
            (data: PageOrderDto) => {
                if (data.content && data.content.length > 0) {
                    this.myOrder = data;
                    this.isAnyOrder = true;
                }
            },
            (error) => {
                console.log('Si è verificato un errore durante il recupero degli ordini dell\'utente:', error);
            }
        );
    }
  getUserInsertion(): void {
    this.insertionService.getInsertionByUserId(this.userId, this.page).subscribe(
      (data: PageBasicInsertionDto) => {
        if (data.content && data.content.length > 0) {
          this.myInsertion = data;
        }
      },
      (error) => {
        console.log('Si è verificato un errore durante il recupero delle inserzioni dell\'utente:', error);
      }
    );
  }
  getUserOffer(): void {
    this.offerService.getAllByUserId(this.page).subscribe(
      (data: Array<BuyingOfferDto>) => {
          this.myOffer = data;

      },
      (error) => {
        console.log('Si è verificato un errore durante il recupero delle offerte dell\'utente:', error);
      }
    );
  }

  showUpdateSection(): void {
    this.showUpdateSectionFlag = !this.showUpdateSectionFlag;
  }

  updatePassword(newPassword: string): void {
    if (this.userId && newPassword) {
      this.userService.updateUserPassword(newPassword).subscribe(
        (success: boolean) => {
          // Password update successful
          console.log('Password updated successfully.');
        },
        (error: any) => {
          // Password update failed
          console.log('Failed to update password:', error);
        }
      );
    }
  }

  updateNickname(newNickname: string): void {
    if (this.userId && newNickname) {

    }
  }

  openInsertionModal() {
    this.InsertionModal = !this.InsertionModal;
  }

  openOfferModal() {
    this.OfferModal = !this.OfferModal;
  }


  openOrderModal() {
    this.OrderModal = !this.OrderModal;
  }

  closeInsertionModal(event: any) {
    if (event.target.classList.contains('insertion-modal')) {
      this.openInsertionModal()
      this.page=0;

    }
  }

  closeOfferModal(event: any) {
    if (event.target.classList.contains('offer-modal')) {
      this.openOfferModal()
      this.page=0;
    }
  }

  closeOrderModal(event: any) {
    if (event.target.classList.contains('order-modal')) {
      this.openOrderModal()
      this.page=0;
    }
  }

  addPageInsertion() {
    this.page+=1;
    this.getUserInsertion()
  }

  addPageOffer() {
    this.page+=1;
    this.getUserOffer()
  }

  addPageOrder() {
    this.page+=1;
    this.getUserOrders()
  }


}
