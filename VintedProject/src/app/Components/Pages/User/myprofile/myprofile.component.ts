import {Component,OnInit} from '@angular/core';
import {InsertionService} from "../../../../api/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../../../api/user.service";
import {forkJoin, Observable, switchMap} from "rxjs";
import {OrderService} from "../../../../api/order.service";
import {CookiesService} from "../../../../api/cookies.service";
import {PageBasicInsertionDto} from "../../../../model/pageBasicInsertionDto";
import {PageOrderDto} from "../../../../model/pageOrderDto";
import {UserDto} from "../../../../model/userDto";
import {BuyingOfferDto} from "../../../../model/buyingOfferDto";
import {OfferService} from "../../../../api/offer.service";
import {PageBuyngOfferDto} from "../../../../model/pageBuyngOfferDto";
import {OrderDto} from "../../../../model/orderDto";



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
  userId: number = Number(this.cookieSevices.getUserId());
  showUpdateSectionFlag = false;

  constructor(
    private insertionService: InsertionService,
    private route: ActivatedRoute,
    private userService: UserService,
    private orderService: OrderService,
    private offerService: OfferService,
    private cookieSevices: CookiesService
  ) {}

  ngOnInit(): void {
    this.cookieSevices.checkUserCookie();
    this.route.paramMap.pipe(
      switchMap((params) => {
        return this.userService.getUserDtoById(this.userId);
      })
    ).subscribe(
      (user: UserDto) => {
        this.user = user;
        // Create observables for each API call
        const insertionObservable = this.insertionService.getInsertionByUserId(this.userId, this.page);
        const offerObservable = this.offerService.getAllByUser();
        const orderObservable = this.orderService.getUserOrders(this.userId);
        // Combine observables using forkJoin
        forkJoin([
          insertionObservable,
          offerObservable,
          orderObservable
        ]).subscribe(
          ([insertionData, offerData, orderData]) => {
            this.myInsertion = insertionData;
            console.log(insertionData)
            this.myOffer = offerData;
            this.myOrder = orderData ;

            if (!this.myInsertion?.empty) {
              this.isAnyInsertion = true;
            }
            if (!this.myOrder) {
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

  showUpdateSection(): void {
    this.showUpdateSectionFlag = !this.showUpdateSectionFlag;
  }

  updatePassword(newPassword: string): void {
    if (this.userId && newPassword) {
      this.userService.updateUserPassword(newPassword, this.userId).subscribe(
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
      /* this.userService.updateUserNickname(newNickname, this.userId, 'body', false).subscribe(
         (success: boolean) => {
           // Nickname update successful
           console.log('Nickname updated successfully.');
         },
         (error: any) => {
           console.log('Failed to update nickname:', error);
         }
       );
     }*/
    }}



}
