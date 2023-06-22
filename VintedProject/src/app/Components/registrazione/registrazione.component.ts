import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {UserService} from "../../service/user.service";
import {AuthService} from "../../service/auth.service";
import {NewUser} from "../../Model/new-user.model";


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
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService) { }

  signin(): void {
    this.authService.signin(this.newUser).subscribe(
      response => {
        // Handle success
      },
      error => {
        // Handle error
      }
    );
  }

  login(): void {
    this.authService.login(this.email, this.password).subscribe(
      response => {
        // Handle success
      },
      error => {
        // Handle error
      }
    );
  }

}


