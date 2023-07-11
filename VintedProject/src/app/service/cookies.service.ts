import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class CookiesService implements OnInit {
  public logStringResult: string | undefined;

  constructor(
    private router: Router,
    private cookieService: CookieService,
    private jwtHelper: JwtHelperService // Inietta il JwtHelperService
  ) {}

  getUserId(){
    return this.cookieService.get("userId",)
  }

   checkUserToken() {
    const token = this.cookieService.get('jwtToken');

    if (token) {
      try {
        const isTokenExpired = this.jwtHelper.isTokenExpired(token);
        if (!isTokenExpired) {
          return true
        } else {
          // Token scaduto
          console.error('Token scaduto');
          this.deleteCookie()
          this.router.navigate(['/login']);
          return false
        }
      } catch (error) {
        // Token non valido
        console.error('Token non valido:', error);
        this.deleteCookie()
        this.router.navigate(['/login']);
        return false
      }
    } else {
      // Token mancante
      this.deleteCookie()
      this.checkUserCookie();
      this.router.navigate(['/login']);
      return false
    }
  }

  checkUserCookie(): void {
    const userCookie = this.cookieService.get('userEmail');
    if (userCookie) {
      this.logStringResult = userCookie;
    } else {
      this.logStringResult = 'Login';
    }
  }

  logStringResultfun() {
    return this.logStringResult;
  }

  deleteCookie(){
    this.cookieService.delete('userId', '/');
    this.cookieService.delete('userEmail', '/');
    this.cookieService.delete('userCity', '/');
    this.cookieService.delete('userFirstName', '/');
    this.cookieService.delete('userLastName', '/');
    this.cookieService.delete('userEmail', '/');
    this.cookieService.delete('jwtToken', '/');
    this.cookieService.delete('userNickname', '/');
    this.checkUserCookie()
  }

  ngOnInit(): void {
    this.checkUserCookie();
    this.checkUserToken();
  }
}
