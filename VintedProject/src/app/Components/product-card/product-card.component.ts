import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent {

  constructor(private router: Router) {

  }

  onImageClick(): void {
    this.router.navigate(['insertion']);
  }

  price = 7
  comletePrice = 8.50
  size = "M"
  brand = "mercato marocchino"

  isHovered = false;

}
