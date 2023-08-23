import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';
import {AuthService} from "./auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";

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
    return this.cookieService.get('jwtToken')
  }

  getRefreshToken() {
    let refreshToken = this.cookieService.get('refreshToken');
    refreshToken.replace(/"/g, '');
    this.authService.getRefreshToken(refreshToken).subscribe(
      response => {
        this.snackBar.open('Tokent ricevuto con successo con successo!', 'OK');
        if (response) {
          this.cookieService.set('jwtToken', response.accessToken!, 1, '/');
          return true
        }
        return false;
      })
  }

  async checkUserToken(): Promise<boolean> {
    const token = this.cookieService.get('jwtToken');
    if (token) {
      try {
        const isTokenExpired = this.jwtHelper.isTokenExpired(token);

        if (!isTokenExpired) {
          return true; // Token valido
        } else {
          await this.getRefreshToken(); // Attendi il completamento del refresh token
          return true; // Ritorna vero dopo il refresh del token
        }
      } catch (error) {
        console.error('Errore durante la verifica del token:', error);
      }

      // Token scaduto o errore durante la verifica
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
    this.checkUserCookie()
  }

  ngOnInit(): void {
    this.checkUserCookie();
    this.checkUserToken();
  }
}
