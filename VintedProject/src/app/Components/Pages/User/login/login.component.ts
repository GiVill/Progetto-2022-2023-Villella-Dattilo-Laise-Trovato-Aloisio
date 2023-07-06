import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../../service/auth.service";
import {LoginUserDto} from "../../../../Model/loginUserDto";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {MainNavComponent} from "../../../Components/main-nav/main-nav.component";
import {CookiesService} from "../../../../service/cookies.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  login: LoginUserDto = {
    email: '',
    password: ''
  };


  constructor(private authService: AuthService,
              private cookieService: CookieService,
              private cookiesService: CookiesService,
              private User: MainNavComponent,
              private router: Router
) { }



  logIn(): void {
    this.authService.login(this.login.email,  this.login.password).subscribe(

      response => {
        const userCookie = this.cookieService.get('username');
        if (!userCookie) {
          this.cookieService.set('username', this.login.email, 1, '/');
          this.cookieService.set('token', String(response), 1, '/');
          this.cookiesService.checkUserCookie();
          this.User.getUserString();
          this.router.navigate(['/']);
        }
      },
      error => {
        // Handle error
        console.log(error)
      }
    );
  }

  ngOnInit(): void {
    this.checkUserCookie()
  }

  checkUserCookie(): void {
    const userCookie = this.cookieService.get('username');

    if (userCookie) {
      this.router.navigate(['/']);
    }
  }


}
