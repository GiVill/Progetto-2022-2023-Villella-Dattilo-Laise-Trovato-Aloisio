import {Component, Input} from '@angular/core';
import { Router } from '@angular/router';
import {BasicInsertion} from "../../Model/basic-insertion.model";


@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent {
  @Input() item: BasicInsertion | undefined;

  isHovered = false;

  constructor(private router: Router) { }

  onImageClick(): void {
    this.router.navigate(['/insertion', this.item?.id]);
  }
}
