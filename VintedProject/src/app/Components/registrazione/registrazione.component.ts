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
    nickName: '',
    email: '',
    password: ''
  };
  login: LogInUser = {
    username: '',
    password: ''
  };


  constructor(private authService: AuthService) { }

  signUp(): void {
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


