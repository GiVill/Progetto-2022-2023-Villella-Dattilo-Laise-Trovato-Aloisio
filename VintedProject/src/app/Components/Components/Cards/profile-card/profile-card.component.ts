import {Component, Input} from '@angular/core';
import {OrderDto} from "../../../../Model/orderDto";
import {Router} from "@angular/router";
import {CartComponent} from "../../../Pages/cart/cart.component";
import {UserDto} from "../../../../Model/userDto";
import {BasicInsertionDto} from "../../../../Model/basicInsertionDto";

@Component({
  selector: 'app-profile-card',
  templateUrl: './profile-card.component.html',
  styleUrls: ['./profile-card.component.css']
})
export class ProfileCardComponent {
  @Input() user: UserDto | undefined;
  @Input() userId: number | undefined;
  @Input() insertion: BasicInsertionDto | undefined; // Receive the BasicInsertionDto input

  isHovered = false;

  constructor(private router: Router) {}

  onImageClick(): void {
    this.router.navigate(['/profile', this.user?.id]);
  }
}
