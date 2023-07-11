import {Component, Input} from '@angular/core';
import {UserDto} from "../../../../Model/userDto";
import {PageBasicInsertionDto} from "../../../../Model/pageBasicInsertionDto";
import {InsertionService} from "../../../../service/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../../../service/user.service";
import {switchMap} from "rxjs";
import {BasicInsertionDto} from "../../../../Model/basicInsertionDto";
import {ImageService} from "../../../../service/image.service";
import {TokenService} from "../../../../service/token.service";
import {PageOrderDto} from "../../../../Model/pageOrderDto";
import {OrderService} from "../../../../service/order.service";
import {CookiesService} from "../../../../service/cookies.service";

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
        return this.userService.getUserById(this.userId);
      })
    ).subscribe(
      (user: UserDto) => {
        this.user = user;
        //TODO Creare sto cazzo di get insertionBUserId e get orderByUserId //
        this.userService.getAllInsertionsByUser(this.userId, this.page).subscribe(
          (data: PageBasicInsertionDto) => {
            this.myInsertion = data;
            console.log(data)
          },
          (error) => {
            console.log('Si è verificato un errore durante il recupero delle altre inserzioni dell\'utente:', error);
          }
        );
      if (this.myInsertion?.empty){
        this.isAnyInsertion=true;
      }
      })

    //TODO orderService deve prendere gli ordini dell'utente pagiagble /////this.page
    this.orderService.getAllOrders(this.userId).subscribe(
      (data: PageOrderDto) => {
        this.myOrder = data;
        console.log(data);
        // Further processing if needed
      },
      (error) => {
        console.log('Si è verificato un errore durante il recupero degli ordini dell\'utente:', error);
      })
    if (this.myOrder?.empty){
      this.isAnyOrder=true;
    }
  }


  show(){
    this.showUpdateSection = !this.showUpdateSection;
  }

  /*
    loadmore() {
      this.page += 1;
      this.userService.getAllInsertionsByUser(this.id,this.page).subscribe((insertions: PageBasicInsertionDto) => {
        // Aggiungo nuovi prodotti alla lista esistente invece di sostituirla
        this.userInsertion?.content?.push(...insertions.content);
        this.processImages(insertions.content);

      });
    }
  */


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
      this.userService.updateUserNickname(this.newNickname, this.userId).subscribe(
        (success: boolean) => {
          // Nickname update successful
          this.isUpdatingNickname = false;
          this.newNickname = '';
          // Handle success response
        },
        (error: any) => {
          console.log(error)
          this.isUpdatingNickname = false;
          // Handle error response
        }
      );
    }
  }






}
