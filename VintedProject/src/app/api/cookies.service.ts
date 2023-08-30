import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';
import {AuthService} from "./auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {TokenDto} from "../model/tokenDto";
import {async} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CookiesService implements OnInit {
  public logStringResult: string | undefined;

  constructor(
    private router: Router,
    private cookieService: CookieService,
    private authService: AuthService,
    private jwtHelper: JwtHelperService,
    private snackBar: MatSnackBar,
  ) {}

  getUserId(){
    return this.cookieService.get("userId",)
  }

  getTokent(){
    this.checkUserToken()
    return this.cookieService.get('jwtToken')
  }

  async getRefreshToken(): Promise<boolean> {
    const refreshTokenValue = this.cookieService.get('refreshToken');
    console.log(refreshTokenValue);

    try {
      const response = await this.authService.getRefreshToken(refreshTokenValue).toPromise();
      this.snackBar.open('Token ricevuto con successo!', 'OK');
      this.cookieService.set('jwtToken', response?.accessToken!, 1, '/');
      this.cookieService.set('refreshToken', response?.refreshToken!, 1, '/');
      console.log("dentro refresh" + response);
      return true;
    } catch (error) {
      console.log(error);
      return false;
    }


  }async checkUserToken(): Promise<boolean> {
    const token = this.cookieService.get('jwtToken');

    if (token) {
      try {
        const isTokenExpired = this.jwtHelper.isTokenExpired(token);
        if (!isTokenExpired) {
          return true; // Token valido
        } else {
          const refreshTokenSuccess = await this.getRefreshToken();
          if (refreshTokenSuccess) {
            console.log("Token Ricevuto");
            return true;
          }
        }
      } catch (error) {
        console.error('Errore durante la verifica del token:', error);
      }

      console.error('Token scaduto o errore durante la verifica');
      this.deleteCookie();
      await this.router.navigate(['/login']);
      return false;
    } else {
      // Token mancante
      console.log('Token mancante');
      this.deleteCookie();
      this.checkUserCookie();
      await this.router.navigate(['/login']);
      return false;
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
    this.cookieService.delete('refreshToken', '/');
    this.checkUserCookie()
  }

  ngOnInit(): void {
    this.checkUserCookie();
    this.checkUserToken();
  }
}
