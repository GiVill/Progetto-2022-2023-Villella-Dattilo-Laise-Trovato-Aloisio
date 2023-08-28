import {Component, Input, OnInit} from "@angular/core";
import {UserDto} from "../../../../model/userDto";
import {BasicInsertionDto} from "../../../../model/basicInsertionDto";
import {Router} from "@angular/router";
import {UserService} from "../../../../api/user.service";
import {BuyingOfferDto} from "../../../../model/buyingOfferDto";
import {PaymentDto} from "../../../../model/paymentDto";
import {InsertionService} from "../../../../api/insertion.service";
import {OfferService} from "../../../../api/offer.service";
import {OrderService} from "../../../../api/order.service";
import {CookiesService} from "../../../../api/cookies.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {forkJoin} from "rxjs";


@Component({
  selector: 'app-offer-profile-card',
  templateUrl: './offer-profile-card.component.html',
  styleUrls: ['./offer-profile-card.component.css']
})
export class OfferProfileCardComponent implements OnInit{
  user: UserDto | undefined;
  @Input() insertion!: BasicInsertionDto; // Receive the BasicInsertionDto input
  @Input() offer?: BuyingOfferDto;
  isHovered = false;
  accepted: boolean = false;
  paymentMethods: PaymentDto.PaymentMethodEnum[] = Object.values(PaymentDto.PaymentMethodEnum);


  constructor(private router: Router,
              private basicInsertion: InsertionService,
              private offerService: OfferService,
              private orderService: OrderService,
              private cookieServices: CookiesService,
              private snackBar: MatSnackBar,
              private userService: UserService
  ) {
  }
  onImageClick(): void {
    this.router.navigate(['/profile', this.user?.id]);
  }

  ngOnInit(): void {
    this.basicInsertion.getInsertionById(this.offer?.insertionId!).subscribe(
      (data: BasicInsertionDto) => {
        this.insertion = data;

        // Create observables for each API call
        const userObservable = this.userService.getUserDtoById(this.insertion?.userId);

        // Combine observables using forkJoin
        forkJoin([userObservable]).subscribe(
          ([user]) => {
            this.user = user;
            console.log(this.user);
          },
          (error) => {
            console.error("Error getting user:", error);
          }
        );
      },
      (error: any) => {
        console.error("Error getting insertion:", error);
      }
    );
  }
}
