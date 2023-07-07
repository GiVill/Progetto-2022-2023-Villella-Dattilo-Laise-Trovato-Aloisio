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
          this.cookieService.delete('username', '/');
          this.cookieService.delete('jwtToken', '/');
          this.router.navigate(['/login']);
          return false
        }
      } catch (error) {
        // Token non valido
        console.error('Token non valido:', error);
        this.cookieService.delete('username', '/');
        this.cookieService.delete('jwtToken', '/');
        this.router.navigate(['/login']);
        return false
      }
    } else {
      // Token mancante
      this.cookieService.delete('username', '/');
      this.cookieService.delete('jwtToken', '/');
      this.checkUserCookie();
      this.router.navigate(['/login']);
      return false
    }
  }

  checkUserCookie(): void {
    const userCookie = this.cookieService.get('username');
    if (userCookie) {
      this.logStringResult = userCookie;
    } else {
      this.logStringResult = 'Login';
    }
  }

  logStringResultfun() {
    return this.logStringResult;
  }

  ngOnInit(): void {
    this.checkUserCookie();
    this.checkUserToken();
  }
}
