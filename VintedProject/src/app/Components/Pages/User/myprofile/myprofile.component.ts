import {Component,OnInit} from '@angular/core';
import {InsertionService} from "../../../../api/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../../../api/user.service";
import {switchMap} from "rxjs";
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
  myOrder: PageOrderDto | undefined;
  myOffer?: BuyingOfferDto[] = [{
    "id": 7,
    "price": 60,
    "status": "APPROVED",
    "insertionId": 2,
    "userId": 7
  }];

  page = 0;
  isAnyInsertion = false;
  isAnyOrder = false;
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
    this.route.paramMap
      .pipe(
        switchMap((params) => {
          return this.userService.getById(this.userId);
        })
      )
      .subscribe(
        (user: UserDto) => {
          this.user = user;
// TODO: Create the functions getInsertionByUserId and getUserOrders in the respective services
          this.insertionService.getInsertionByUserId(this.userId, this.page).subscribe(
            (data: PageBasicInsertionDto) => {
              this.myInsertion = data;
              if (this.myInsertion?.empty) {
                this.isAnyInsertion = true;
              }
              console.log(this.myInsertion)
            },
            (error) => {
              console.log('Si è verificato un errore durante il recupero delle altre inserzioni dell\'utente:', error);
            }
          );

          this.orderService.getUserOrders(this.userId, this.page).subscribe(
            (data: PageOrderDto) => {
              this.myOrder = data;
              if (this.myOrder?.empty) {
                this.isAnyOrder = true;
              }
            },
            (error) => {
              console.log('Si è verificato un errore durante il recupero degli ordini dell\'utente:', error);
            }
          );
        }
      );

    /*this.offerService.allId(this.userId).subscribe(
      (value: BuyingOfferDto) => {
        this.myOffer?.push(value);
        console.log(this.myOffer)
      },
      (error) => {
        console.log('Si è verificato un errore durante il recupero delle offerte dell\'utente:', error);
      }
    );*/

  }


  getUserOrders(): void {
    this.orderService.getUserOrders(this.userId, this.page).subscribe(
      (data: PageOrderDto) => {
        this.myOrder = data;
        if (this.myOrder?.empty) {
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
