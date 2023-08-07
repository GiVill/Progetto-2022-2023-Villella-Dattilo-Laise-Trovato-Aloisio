import {Component, Input, OnInit} from "@angular/core";
import {BasicInsertionDto} from "../../../../model/basicInsertionDto";
import {Router} from "@angular/router";
import {CookiesService} from "../../../../api/cookies.service";
import {InsertionService} from "../../../../api/insertion.service";

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

  constructor(private router: Router,
              private cookiesService: CookiesService,
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
        console.log("Errore durante la generazione", error);
      })
  }


}
