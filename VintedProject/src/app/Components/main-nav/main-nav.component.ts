import { Component } from '@angular/core';

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
})
export class MainNavComponent {
  showFiller!: boolean;
  hideMenu = true;

  showCategories(){
    this.hideMenu = !this.hideMenu;
  }

}
