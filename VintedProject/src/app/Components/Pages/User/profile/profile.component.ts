import {Component, Input, OnInit} from '@angular/core';
import {switchMap} from "rxjs";
import {UserDto} from "../../../../model/userDto";
import {PageBasicInsertionDto} from "../../../../model/pageBasicInsertionDto";
import {InsertionService} from "../../../../api/insertion.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../../api/user.service";
import {ErrorService} from "../../../../api/error.service";
import {CookiesService} from "../../../../api/cookies.service";
;


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  users: UserDto[] = [];
  @Input() user: UserDto | undefined;
  userInsertion: PageBasicInsertionDto | undefined;
  page= 0

  id: number | undefined;
  modalOpen = false;
  modalImage: string | undefined;

  constructor(
    private insertionService: InsertionService,
    private route: ActivatedRoute,
    private userService: UserService,
    private router: Router,
    private error: ErrorService,
    private cookieservice: CookiesService,
  ) {}


  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap((params) => {
        this.id = Number(params.get('userid'));
        if (this.id==Number(this.cookieservice.getUserId()))
          this.router.navigate(['/myprofile'])
        return this.userService.getUserDtoById(this.id);
      })
    ).subscribe(
      (user: UserDto) => {
        this.user = user;
        console.log(this.user)
        this.insertionService.getInsertionByUserId(this.id!, this.page).subscribe(
          (data: PageBasicInsertionDto) => {
            this.userInsertion = data;
            const userIds = this.userInsertion.content!.map((insertion) => insertion.userId).filter((id, index, array) => array.indexOf(id) === index);
            userIds.forEach((userId) => {
                this.userService.getUserDtoById(userId).subscribe((user: UserDto) => {
                  this.users.push(user);
                });


              },
              (error) => {
                console.log('Si è verificato un errore durante il recupero delle altre inserzioni dell\'utente:', error);
              }
            );
          },
          (error) => {

            console.log('Si è verificato un errore durante il recupero dell\'utente:', error);
          }
        );



      },  (error) => {
        if (!this.error.redirectToErrorPage(error)) {
          this.error.redirectToErrorPage(error)
        }
      console.log('Si è verificato un errore durante il recupero delle altre inserzioni dell\'utente:', error);
    }
      );
  }

  getUserByUserId(userId: number): UserDto  {
    return <UserDto>this.users.find(user => user.id === userId);
  }


}
