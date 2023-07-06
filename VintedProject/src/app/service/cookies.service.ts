import {Injectable, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class CookiesService implements  OnInit{
  public logStringResult: string | undefined;

  constructor(
    private router: Router,
    private cookieService: CookieService){}

  checkUser() {
    //TODO controllo del token
  }

  checkUserCookie(): void {
    const userCookie = this.cookieService.get('username');

    if (userCookie) {
      this.logStringResult = userCookie;
    } else {
      this.logStringResult = 'Login';
    }
  }

  logStringResultfun(){
    return this.logStringResult;
  }

  ngOnInit(): void {
    this.checkUserCookie()
  }
}
