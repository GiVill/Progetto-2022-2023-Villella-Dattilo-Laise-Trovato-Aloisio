import {Component, Input, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {BasicInsertionDto} from "../../../../Model/basicInsertionDto";

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit{
  @Input() item: BasicInsertionDto | undefined;

  imageName !: String;
  isHovered = false;

  constructor(private router: Router) { }
  ngOnInit(): void {
    if(this.item?.imageName != null){
      this.imageName = 'https://localhost:8010/vintedProject-api/v1/images/' + this.item.imageName
    } else {
      this.imageName = 'assets/resources/vestito.jpg'
    }
  }

  onImageClick(): void {
    this.router.navigate(['/insertion', this.item?.id]);
  }


}
