import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class CookiesService implements OnInit {
  public logStringResult: string | undefined;

  constructor(
    private router: Router,
    private cookieService: CookieService,
    private authService: AuthService,
    private jwtHelper: JwtHelperService // Inietta il JwtHelperService
  ) {}

  getUserId(){
    return this.cookieService.get("userId",)
  }

  getTokent(){
    return this.cookieService.get('jwtToken')
  }

  async checkUserToken(): Promise<boolean> {
    const token = this.cookieService.get('jwtToken');

    if (token) {
      try {
        const isTokenExpired = this.jwtHelper.isTokenExpired(token);

        if (!isTokenExpired) {
          return true; // Token valido
        } else {
          try {
            const newToken = await this.authService.getRefreshToken(token).toPromise();

            if (newToken) {
              const isNewTokenExpired = this.jwtHelper.isTokenExpired(newToken);

              if (!isNewTokenExpired) {
                // Aggiorna il token esistente
                this.cookieService.set('jwtToken', newToken);
                return true;
              }
            }

            // Token di aggiornamento non valido o mancante
            console.error('Token di aggiornamento non valido o mancante');
          } catch (error) {
            console.error('Errore durante il recupero del nuovo token:', error);
          }

          // Token scaduto
          console.error('Token scaduto');
          this.deleteCookie();
          await this.router.navigate(['/login']);
          return false;
        }
      } catch (error) {
        // Token non valido
        console.error('Token non valido:', error);
      }
    } else {
      // Token mancante
      console.log('Token mancante');
      this.deleteCookie();
      this.checkUserCookie();
      await this.router.navigate(['/login']);
    }

    return false; // Restituisce falso se si verificano errori
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
