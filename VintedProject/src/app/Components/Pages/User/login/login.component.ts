import {Component, OnInit, Renderer2} from "@angular/core";
import {LoginUserDto} from "../../../../model/loginUserDto";
import {NewUserDto} from "../../../../model/newUserDto";
import {AuthService} from "../../../../api/auth.service";
import {CookieService} from "ngx-cookie-service";
import {CookiesService} from "../../../../api/cookies.service";
import {MainNavComponent} from "../../../Components/main-nav/main-nav.component";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ErrorService} from "../../../../api/error.service";
import {Configuration} from "../../../../configuration";




@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  isFlipped: boolean = false;
  passwordCheck: string = '';
  isRegistrationMode: boolean = false;




  login: LoginUserDto = {
    email: '',
    password: ''
  };


  newUser: NewUserDto = {
    password: '',
    nickname: '',
    firstname: '',
    email: '',
    lastname: '',
    addressStreet: '',
    addressNumber: 0,
    addressCity: '',
    addressCap: 0

  };
  private configuration: Configuration ={
    accessToken: "",
    apiKeys: {},
    basePath: "",
    password: "",
    username: "",
    withCredentials: false,
    isJsonMime(mime: string): boolean {
      return false;
    },
    selectHeaderAccept(accepts: string[]): string | undefined {
      return undefined;
    },
    selectHeaderContentType(contentTypes: string[]): string | undefined {
      return undefined;
    }

  };



  constructor(private authService: AuthService,
              private cookieService: CookieService,
              private cookiesService: CookiesService,
              private User: MainNavComponent,
              private router: Router,
              private snackBar: MatSnackBar,
              private error: ErrorService,

) { }



  logIn(): void {
    this.authService.login(this.login).subscribe(
      response => {
        console.log(response)
        const userCookie = this.cookieService.get('userEmail');
        if (!userCookie) {
          // Salva le informazioni dell'utente nei cookie
          if (response.userDto!.id != null) {
          this.cookieService.set('userId', response.userDto!.id.toString(), 1, '/', '', true, 'Lax');}
          if (response.userDto!.email != null) {
            this.cookieService.set('userEmail', response.userDto!.email, 1, '/',  '', true, 'Lax');}
          if (response.userDto!.addressCity != null) {
            this.cookieService.set('userCity', response.userDto!.addressCity, 1, '/',  '', true, 'Lax');}
          this.cookieService.set('userFirstName', response.userDto!.firstName, 1, '/',  '', true, 'Lax');
          this.cookieService.set('userLastName', response.userDto!.lastName, 1, '/' , '', true, 'Lax');
          if (response.accessToken != null) {
            this.cookieService.set('jwtToken', response.accessToken, 1, '/', '', true, 'Lax');
            if (response.refreshToken != null) {
              this.cookieService.set('refreshToken', response.refreshToken, 1, '/', '', true, 'Lax');
            }
          }
          this.cookiesService.checkUserCookie();
          this.User.getUserString();
          this.configuration.accessToken=this.cookiesService.getTokent;
          this.router.navigate(['/']);
        }
      },
      error => {
        console.log(error);
        this.snackBar.open('Credenziali errate!', 'OK');
      }
    );
    console.log(this.configuration.accessToken)
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

  flipCard() {
    this.isFlipped = !this.isFlipped
    this.isRegistrationMode = !this.isRegistrationMode;
  }

  signUp(): void {
    this.authService.signUp(this.newUser).subscribe(
      () => {
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
    if (this.newUser.email!.length==0 ||
        this.newUser.password?.length==0 ||
        this.passwordCheck.length==0){
      return false;
    }
    return true;
  }




}
