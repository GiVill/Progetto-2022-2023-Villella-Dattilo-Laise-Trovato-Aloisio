import { Component } from '@angular/core';
import {AuthService} from "../../../../service/auth.service";
import {LogInUser} from "../../../../Model/log-in-user.model";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  login: LogInUser = {
    username: '',
    password: ''
  };


  constructor(private authService: AuthService,
              private cookieService: CookieService,
              private router: Router
) { }



  logIn(): void {
    this.authService.login(this.login.username,  this.login.password).subscribe(
      response => {
          const TokenResponse  = response;
        this.cookieService.set('username', this.login.username, 1, '/');
        this.cookieService.set('username', String(TokenResponse), 1, '/');
        this.router.navigate(['/']);

      },
      error => {
        // Handle error
      }
    );
  }


}
