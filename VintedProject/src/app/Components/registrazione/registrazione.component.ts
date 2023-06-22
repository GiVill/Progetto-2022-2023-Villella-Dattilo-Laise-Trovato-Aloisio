import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {UserService} from "../../service/user.service";
import {AuthService} from "../../service/auth.service";
import {NewUser} from "../../Model/new-user.model";
import  {LogInUser} from "../../Model/log-in-user.model";

@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrls: ['./registrazione.component.css']
})

export class RegistrazoneComponent {
  newUser: NewUser = {
    username: '',
    email: '',
    password: ''
  };
  login: LogInUser = {
    username: '',
    password: ''
  };


  constructor(private authService: AuthService) { }

  signIn(): void {
    this.authService.signin(this.newUser).subscribe(
      response => {
        // Handle success
      },
      error => {
        // Handle error
      }
    );
  }

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


