import { Component } from '@angular/core';
import {AuthService} from "../../../../service/auth.service";
import {Router} from "@angular/router";
import {NewUser} from "../../../../Model/new-user.model";

@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrls: ['./registrazione.component.css']
})
export class RegistrazioneComponent {
  passwordsMatch: boolean = true;

  newUser: NewUser = {
    nickName: '',
    email: '',
    password: '',
    passwordCheck: ''
  };


  constructor(private authService: AuthService,
              private router: Router) { }

  signUp(): void {
    if (this.newUser.password !== this.newUser.passwordCheck) {
      this.passwordsMatch = false;
      return;
    }
    this.authService.signUp(this.newUser).subscribe(
      response => {
        console.log("OK => ",response)
        // Handle success
      },
      error => {
        console.log("ERRORE REGISTRAZIONE => ",error)
        // Handle error
      }
    );
    this.router.navigate(['/']);  }
}
