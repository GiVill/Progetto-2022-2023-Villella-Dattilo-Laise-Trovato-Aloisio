import {Component, Input} from "@angular/core";
import {UserDto} from "../../../../model/userDto";
import {BasicInsertionDto} from "../../../../model/basicInsertionDto";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {CookiesService} from "../../../../api/cookies.service";
import {InsertionService} from "../../../../api/insertion.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MyprofileComponent} from "../../../Pages/User/myprofile/myprofile.component";


@Component({
  selector: 'app-profile-card',
  templateUrl: './profile-card.component.html',
  styleUrls: ['./profile-card.component.css']
})
export class ProfileCardComponent {
  @Input() user: UserDto | undefined;
  @Input() profile:Boolean | undefined;
  @Input() userId: number | undefined;
  @Input() insertion: BasicInsertionDto | undefined; // Receive the BasicInsertionDto input
  myUserId=  Number(this.cookieService.getUserId())
    isHovered = false;

  constructor(private router: Router,
              private insertionService: InsertionService,
              private snackBar: MatSnackBar,
              private myProfile: MyprofileComponent,
              private cookieService: CookiesService) {}

  onImageClick(): void {
    this.router.navigate(['/profile', this.user?.id]);
  }

  deleteInsertion() {
    this.insertionService.userDeleteInsertion(this.insertion!).subscribe(response => {
      if (this.profile){
        this.myProfile.getUserInsertion()
      }
        this.snackBar.open("Inserzione eliminata" , "OK")
      },
      error => {
        this.snackBar.open("Errore durante l'eliminazione" , "Riprovare")
      })

  }
}
