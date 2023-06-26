import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
})

export class MainNavComponent {

  showFiller!: boolean;

  showSubMenu: boolean = false;

  constructor(
              private router: Router,
              private cookieService: CookieService){}


  showMenu() {
    this.showSubMenu = true;
  }

  hideMenu() {
    this.showSubMenu = false;
  }


  redirectToSearch(searchKeyword: string) {

    const formattedSearch = searchKeyword.toLowerCase();
    this.router.navigate(['/search', formattedSearch]);

  }




}
