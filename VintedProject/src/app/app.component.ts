import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Vinted-Front-End';
  isAdmin: boolean = false;
  isUserLogged=false;


}
