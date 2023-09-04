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
import {ImageService} from "../../../../api/image.service";
import {ImagesUserBody} from "../../../../model/imagesUserBody";



@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.css']
})
export class MyprofileComponent implements OnInit{
  user: UserDto | undefined;
  myInsertion!: PageBasicInsertionDto;
  myOrder?: PageOrderDto;
  myOffer?:Array<BuyingOfferDto>;
  page = 0;
  isAnyInsertion = false;
  isAnyOrder = false;
  isAnyOffer = false;
  userId = Number(this.cookieSevices.getUserId());
  showUpdateSectionFlag = false;
  InsertionModal=false
  OfferModal = false
  OrderModal = false
  private img: File | undefined



  constructor(
    private insertionService: InsertionService,
    private route: ActivatedRoute,
    private userService: UserService,
    private imageService: ImageService,
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
    this.insertionService.getMyInsertion(this.page).subscribe(
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
    console.log(this.page)
    this.offerService.getAllByUser().subscribe(
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

  subPageInsertion(){
    this.page-=1;
    this.getUserInsertion()
  }

  addPageOffer() {
    this.page+=1;
    this.getUserOffer()
  }

  subPageOffer(){
    this.page-=1;
    this.getUserOffer()
  }

  addPageOrder() {
    this.page+=1;
    this.getUserOrders()
  }

  subPageOrder(){
    this.page-=1;
    this.getUserOrders()
  }

  caricaFoto(event: any) {
    const fileInput = event.target;
    const files = fileInput.files;

    if (files && files.length > 0) {
      this.img = files[0]

      this.updateImage();
    }
  }

  updateImage() {

    this.imageService.insertUserImage(this.img!).subscribe(
      (response) => {
       console.log(response)
      },
      (error) => {
        console.log('Si è verificato un errore durante ilcaricamento dell immagine:', error);
      }
    );
  }
}
