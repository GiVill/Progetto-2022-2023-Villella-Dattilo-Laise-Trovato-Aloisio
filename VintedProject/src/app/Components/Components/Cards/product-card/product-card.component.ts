import {Component, Input, OnInit} from "@angular/core";
import {BasicInsertionDto} from "../../../../model/basicInsertionDto";
import {Router} from "@angular/router";
import {CookiesService} from "../../../../api/cookies.service";
import {InsertionService} from "../../../../api/insertion.service";
import {BuyingOfferDto} from "../../../../model/buyingOfferDto";
import {OfferService} from "../../../../api/offer.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit{
  @Input() item: BasicInsertionDto | undefined;

  imageName !: String;
  isHovered = false;
  myid= Number(this.cookiesService.getUserId());
  offers?: BuyingOfferDto[]
  offerModalOpen = false;

  constructor(private router: Router,
              private cookiesService: CookiesService,
              private offerService: OfferService,
              private snackBar: MatSnackBar,
              private insertionService: InsertionService) { }
  ngOnInit(): void {
    if(this.item?.imageName != null){
      this.imageName = 'https://localhost:8010/vintedProject-api/v1/images/' + this.item.imageName
    } else {
      this.imageName = 'assets/resources/vestito.jpg'
    }

    if (this.item?.description && this.item.description.length > 15) {
      this.item.description = this.item.description.substr(0, 15) + '...';
    }
  }

  onImageClick(): void {
    this.router.navigate(['/insertion', this.item?.id]);
  }

  share(){
    this.insertionService.generateCapabilities(Number(this.item?.id)).subscribe(response => {
        console.log("Capability generata", response);
        //TODO
      },
      error => {
      if (error.status == 200){
        this.snackBar.open("Codice copiato nella clipboard.")
      }
        console.log("Errore durante la generazione", error);
      })
  }


  offer() {
    this.offerService.allInsertionId(Number(this.item?.id)).subscribe(
      (data: BuyingOfferDto[]) => {
        this.offers = data;
        this.sortOffersByPriceDescending();
        console.log(data)
      },
      (error) => {
        console.log('Si è verificato un errore durante il recupero delle altre offerte', error);
      }
    );
  }

  openOfferModal(): void {
    this.offerModalOpen = true;
    this.offer();
  }

  closeOfferModal(event: any): void {
    if (event.target.classList.contains('offer-message-modal')) {
      this.offerModalOpen = false;
    }

  }

  sortOffersByPriceDescending() {
    this.offers?.sort((a, b) => b.price - a.price); // Ordina le offerte per prezzo decrescente
  }

  acceptOffer() {

  }

  deleteOffer(id: number | undefined) {
    console.log(id)
    this.offerService._delete(Number(id)).subscribe(response => {
        this.offer()
        this.snackBar.open("Offerta rifiutata" , "OK")
      },
      error => {
        if (error.status == 200){
          this.offer()
          this.snackBar.open("Offerta rifiutata" , "OK")
        }
        this.snackBar.open("Errore durante l'eliminazione dell'offerta" , "Riprovare")
      })
  }
}
