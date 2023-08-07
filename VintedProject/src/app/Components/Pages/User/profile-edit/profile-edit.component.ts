import {Component, EventEmitter, Input, Output} from "@angular/core";
import {UserDto} from "../../../../model/userDto";

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent {
  @Input() user: UserDto | undefined;
  @Output() updatePassword: EventEmitter<string> = new EventEmitter<string>();
  @Output() updateNickname: EventEmitter<string> = new EventEmitter<string>();
  newPassword: string = '';
  newNickname: string = '';

  constructor() {}

  savePassword(): void {
    this.updatePassword.emit(this.newPassword);
    this.newPassword = '';
  }

  saveNickname(): void {
    this.updateNickname.emit(this.newNickname);
    this.newNickname = '';
  }

}
