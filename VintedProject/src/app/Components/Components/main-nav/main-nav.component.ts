import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
})

export class MainNavComponent implements OnInit{

  showFiller!: boolean;
  logStringResult: string | undefined;
  showSubMenu: boolean = false;

  constructor(
              private router: Router,
              private cookieService: CookieService){}


  ngOnInit(): void {
    this.checkUserCookie()
  }


  showMenu() {
    this.showSubMenu = true;
  }

  hideMenu() {
    this.showSubMenu = false;
  }



checkUserCookie(): void {
    const userCookie = this.cookieService.get('username');

    if (userCookie) {
      this.logStringResult = userCookie;
    } else {
      this.logStringResult = 'Login';
    }
  }


  redirectToSearch(searchKeyword: string) {

    const formattedSearch = searchKeyword.toLowerCase();
    this.router.navigate(['/search', formattedSearch]);

  }




}
