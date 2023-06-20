import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {UserService} from "../../service/user.service";


@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrls: ['./registrazione.component.css']
})

export class RegistrazoneComponent {
   id: number | undefined;
   nickName: string | undefined;
   email: string | undefined;


  constructor(private http: HttpClient,
              private userService: UserService) {}

  register() {
    const userData = {
      id: this.id,
      nickName: this.nickName,
      email: this.email,

    };
    this.userService.createUser(userData);
  }


}


