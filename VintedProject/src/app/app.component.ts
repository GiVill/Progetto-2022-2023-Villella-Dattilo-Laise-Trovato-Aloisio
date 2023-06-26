import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Vinted-Front-End';
  isUserLogged=false;

  checkUser() {
    //TODO controllo del token
  }

}
