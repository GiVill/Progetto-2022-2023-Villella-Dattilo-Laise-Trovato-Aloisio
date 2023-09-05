import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {AppComponent} from "../../../app.component";
import {CookieService} from "ngx-cookie-service";
import {CookiesService} from "../../../api/cookies.service";
import {UserService} from "../../../api/user.service";

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
})

export class MainNavComponent implements OnInit{

  showFiller!: boolean;
  showSubMenu: boolean = false;
  logStringResult: string | undefined;
  isAdmin: boolean = false;

  constructor(
              private router: Router,
              private appComponent: AppComponent,
              private cookiesService: CookieService,
              private cookieService: CookiesService){}


  ngOnInit(): void {
    this.cookieService.checkUserCookie()
    this.logStringResult = this.cookieService.logStringResultfun()
    if (this.cookieService.checkAdmin())
      this.isAdmin=true;
    else{
      this.isAdmin=false;
    }
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
