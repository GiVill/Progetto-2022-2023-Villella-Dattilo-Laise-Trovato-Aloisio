import {Component, Input} from '@angular/core';
import {InsertionService} from "../../../../service/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../../../service/user.service";
import {switchMap} from "rxjs";
import {OrderService} from "../../../../service/order.service";
import {CookiesService} from "../../../../service/cookies.service";
import {PageBasicInsertionDto} from "../../../../Model/pageBasicInsertionDto";
import {PageOrderDto} from "../../../../Model/pageOrderDto";
import {UserDto} from "../../../../Model/userDto";


@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.css']
})
export class MyprofileComponent {

  @Input() user: UserDto | undefined;
  myInsertion: PageBasicInsertionDto | undefined;
  myOrder: PageOrderDto | undefined
  page= 0
  isAnyInsertion=false;
  isAnyOrder=false;
  newPassword: string = '';
  newNickname: string = '';
  userId: number = Number(this.cookieSevices.getUserId());
  isUpdatingPassword: boolean = false;
  isUpdatingNickname: boolean = false;
  showUpdateSection =false;


  constructor(
    private insertionService: InsertionService,
    private route: ActivatedRoute,
    private userService: UserService,
    private  orderService: OrderService,
    private cookieSevices: CookiesService,
  ) {}


  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap((params) => {
        return this.userService.getById(this.userId);
      })
    ).subscribe(
      (user: UserDto) => {
        this.user = user;
        //TODO Creare sto cazzo di get insertionBUserId e get orderByUserId //
        this.insertionService.getInsertionByUserId(this.userId, this.page).subscribe(
          (data: PageBasicInsertionDto) => {
            this.myInsertion = data;
          },
          (error) => {
            console.log('Si è verificato un errore durante il recupero delle altre inserzioni dell\'utente:', error);
          }
        );
        if (this.myInsertion?.empty) {
          this.isAnyInsertion = true;
        }
      })

    this.orderService.getUserOrders(this.userId, this.page).subscribe(
      (data: PageOrderDto) => {
        this.myOrder = data;
        console.log(data);
        console.log(this.myOrder)

        if (this.myOrder?.empty) {
          this.isAnyOrder = true;
        }
      },
      (error) => {
        console.log('Si è verificato un errore durante il recupero degli ordini dell\'utente:', error);
      }
    );
    console.log(this.myOrder)
  }

  show(){
    this.showUpdateSection = !this.showUpdateSection;
  }



  updatePassword() {
    if (this.userId && this.newPassword) {
      this.isUpdatingPassword = true;
      this.userService.updateUserPassword(this.newPassword, this.userId).subscribe(
        (success: boolean) => {
          // Password update successful
          this.isUpdatingPassword = false;
          this.newPassword = '';
          // Handle success response
        },
        (error: any) => {
          // Password update failed
          this.isUpdatingPassword = false;
          console.log(error)
        }
      );
    }
  }

  updateNickname() {
    if (this.userId && this.newNickname) {
      this.isUpdatingNickname = true;
      this.userService.updateUserNickname(this.newNickname, this.userId, 'body', false).subscribe(
        (success: boolean) => {
          // Nickname update successful
          this.isUpdatingNickname = false;
          this.newNickname = '';
          // Handle success response
        },
        (error: any) => {
          console.log(error);
          this.isUpdatingNickname = false;
          // Handle error response
        }
      );
    }
  }







}
