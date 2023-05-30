import { Component } from '@angular/core';


@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent {

  price = 7
  comletePrice = 8.50
  size = "M"
  brand = "mercato marocchino"
  
}
