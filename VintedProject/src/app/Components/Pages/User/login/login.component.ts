import {Component, OnInit, Renderer2} from '@angular/core';
import {AuthService} from "../../../../service/auth.service";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {MainNavComponent} from "../../../Components/main-nav/main-nav.component";
import {CookiesService} from "../../../../service/cookies.service";
import { MatSnackBar } from '@angular/material/snack-bar';
import {ErrorService} from "../../../../service/error.service";
import {LoginUserDto} from "../../../../model/loginUserDto";
import {NewUserDto} from "../../../../model/newUserDto";


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

  passwordCheck: string = '';


  login: LoginUserDto = {
    email: '',
    password: ''
  };


  newUser: NewUserDto = {
    password: '',
    nickName: '',
    firstName: '',
    email: '',
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
    this.authService.login(this.login).subscribe(
      response => {
        const userCookie = this.cookieService.get('username');
        if (!userCookie) {
          // Salva le informazioni dell'utente nei cookie
          if (response.userDto!.id != null) {
          this.cookieService.set('userId', response.userDto!.id.toString(), 1, '/');}
          if (response.userDto!.email != null) {
            this.cookieService.set('userEmail', response.userDto!.email, 1, '/');}
          if (response.userDto!.addressCity != null) {
            this.cookieService.set('userCity', response.userDto!.addressCity, 1, '/');}
          this.cookieService.set('userFirstName', response.userDto!.firstName, 1, '/');
          this.cookieService.set('userLastName', response.userDto!.lastName, 1, '/');
          this.cookieService.set('userNickname', response.userDto!.nickName, 1, '/');
          if (response.access_token != null) {
            this.cookieService.set('jwtToken', response.access_token, 1, '/');
          }else console.log("Non Arriva!")
          this.cookiesService.checkUserCookie();
          this.User.getUserString();
          this.router.navigate(['/']);
        }
      },
      error => {
        console.log(error);
        this.snackBar.open('Credenziali errate!', 'OK');
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
      () => {
        // Handle success
        this.snackBar.open('Registrazione completata con successo!', 'OK');
        this.flipCard();
      },
      (error: any) => {
        console.log('ERRORE REGISTRAZIONE => ', error);
        if (!this.error.redirectToErrorPage(error)) {
          this.snackBar.open('Errore registrazione, riprova più tardi!', 'OK');
        }
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
        this.newUser.email!.length==0 ||
        this.newUser.password.length==0 ||
        this.passwordCheck.length==0){
      return false;
    }
    return true;
  }


}
