import {Component, EventEmitter, Input, Output} from "@angular/core";
import {UserDto} from "../../../../model/userDto";
import {UserService} from "../../../../api/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent {
  @Input() user: UserDto | undefined;
  newPassword: string = '';
  checkPassword: string = '';

  constructor(private userService: UserService,
              private snackBar: MatSnackBar) {}

  savePassword(): void {
    if (this.newPassword == this.checkPassword){
      this.userService.updateUserPassword(this.newPassword).subscribe(() => {
        this.snackBar.open("Password aggiornata" , "OK")
        this.newPassword = '';
        this.checkPassword = '';
      })
    } else{
      this.snackBar.open("Le password non corrispondono" , "OK, RIPROVO")

    }

  }



}
