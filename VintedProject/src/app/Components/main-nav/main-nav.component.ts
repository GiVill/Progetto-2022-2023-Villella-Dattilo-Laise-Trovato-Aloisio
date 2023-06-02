import { Component } from '@angular/core';
import { ProductCardComponent } from '../product-card/product-card.component';

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
})

export class MainNavComponent {
  showFiller!: boolean;

  showSubMenu: boolean = false;

  showMenu() {
    this.showSubMenu = true;
  }

  hideMenu() {
    this.showSubMenu = false;
  }



}
