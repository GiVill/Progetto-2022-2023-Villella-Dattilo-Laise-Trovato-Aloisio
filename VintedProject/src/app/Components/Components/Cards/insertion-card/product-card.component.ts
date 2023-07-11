import {Component, Input} from '@angular/core';
import { Router } from '@angular/router';
import {BasicInsertionDto} from "../../../../model/basicInsertionDto";

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent {
  @Input() item: BasicInsertionDto | undefined;

  isHovered = false;

  constructor(private router: Router) { }

  onImageClick(): void {
    this.router.navigate(['/insertion', this.item?.id]);
  }
}
