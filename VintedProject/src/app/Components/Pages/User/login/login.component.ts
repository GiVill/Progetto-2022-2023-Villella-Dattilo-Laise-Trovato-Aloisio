import { Component } from '@angular/core';
import {AuthService} from "../../../../service/auth.service";
import {LogInUser} from "../../../../Model/log-in-user.model";


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


  constructor(private authService: AuthService) { }



  logIn(): void {
    this.authService.login(this.login.username,  this.login.password).subscribe(
      response => {

      },
      error => {
        // Handle error
      }
    );
  }


}
