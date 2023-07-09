import {Component, OnInit, Renderer2} from '@angular/core';
import {AuthService} from "../../../../service/auth.service";
import {LoginUserDto} from "../../../../Model/loginUserDto";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {MainNavComponent} from "../../../Components/main-nav/main-nav.component";
import {CookiesService} from "../../../../service/cookies.service";
import { NewUserDto } from 'src/app/Model/newUserDto';
import { MatSnackBar } from '@angular/material/snack-bar';
import {ErrorService} from "../../../../service/error.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  isFlipped: boolean = false;

  passwordsMatch: boolean = true;

  isAllFilledLogin: boolean = false;

  isAllFilledRegistration: boolean = false;


  login: LoginUserDto = {
    email: '',
    password: ''
  };


  newUser: NewUserDto = {
    nickName: '',
    email: '',
    password: '',
    passwordCheck: ''
  };


  constructor(private authService: AuthService,
              private cookieService: CookieService,
              private cookiesService: CookiesService,
              private User: MainNavComponent,
              private router: Router,
              private renderer: Renderer2,
              private snackBar: MatSnackBar,
              private error: ErrorService,
) { }



  logIn(): void {
    this.authService.login(this.login.email,  this.login.password).subscribe(

      response => {
        const userCookie = this.cookieService.get('username');
        if (!userCookie) {
          this.cookieService.set('username', this.login.email, 1, '/');
          this.cookieService.set('jwtToken', response.access_token, 1, '/');
          this.cookiesService.checkUserCookie();
          this.User.getUserString();
          this.router.navigate(['/']);
        }
      },
      error => {
        // Handle error
        console.log(error)
        if (!this.error.redirectToErrorPage(error))
          this.snackBar.open("Credenziali errate!","OK");
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

  flipCard(): void {
    this.isFlipped = !this.isFlipped;
  }

  signUp(): void {
    this.authService.signUp(this.newUser).subscribe(
      response => {
        // Handle success
        this.snackBar.open("Registrazione completata con successo!", "OK");
        this.flipCard();
      },
      error => {
        console.log("ERRORE REGISTRAZIONE => ", error);
        if (!this.error.redirectToErrorPage(error))
          this.snackBar.open("Errore registrazione, riprova più tardi!", "OK");

      }
    );
  }

  isLoginFormValid(): boolean {
    if (this.login.email.length==0 || this.login.password.length==0){
      return false;
    }
    return true;
  }

  isRegistrationFormValid(): boolean {
    if (this.newUser.nickName.length==0 ||
        this.newUser.email.length==0 ||
        this.newUser.password.length==0 ||
        this.newUser.passwordCheck.length==0){
      return false;
    }
    return true;
  }


}
