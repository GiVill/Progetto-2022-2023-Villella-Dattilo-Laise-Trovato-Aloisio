import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {AppComponent} from "../../../app.component";
import {CookiesService} from "../../../service/cookies.service";

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
})

export class MainNavComponent implements OnInit{

  showFiller!: boolean;
  showSubMenu: boolean = false;
  logStringResult: string | undefined;

  constructor(
              private router: Router,
              private appComponent: AppComponent,
              private cookiesService: CookieService,
              private cookieService: CookiesService){}


  ngOnInit(): void {
    this.cookieService.checkUserCookie()
    this.logStringResult = this.cookieService.logStringResultfun()
    //this.getUserString()
  }

  getUserString(){
    return this.logStringResult = this.cookieService.logStringResultfun()
  }


  showMenu() {
    this.showSubMenu = true;
  }

  hideMenu() {
    this.showSubMenu = false;
  }



  logout(): void {
    this.cookieService.deleteCookie()
    this.router.navigate(['/login']);
    this.getUserString()
  }


  redirectToSearch(searchKeyword: string) {
    this.cookieService.checkUserCookie();
    const formattedSearch = searchKeyword.toLowerCase();
    this.router.navigate(['/search', formattedSearch]);

  }




}
