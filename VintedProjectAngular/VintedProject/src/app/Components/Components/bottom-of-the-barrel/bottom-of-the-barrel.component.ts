import { Component } from '@angular/core';

@Component({
  selector: 'app-bottom-of-the-barrel',
  templateUrl: './bottom-of-the-barrel.component.html',
  styleUrls: ['./bottom-of-the-barrel.component.css']
})
export class BottomOfTheBarrelComponent {
  siteName: string = 'Ocarina Coders';
  address: string = 'Ponte Pietro Bucci';
  email: string = 'info@daidaidai.com';
  phone: string = '+39 123-456-7890';
  currentYear: number = new Date().getFullYear();
}
