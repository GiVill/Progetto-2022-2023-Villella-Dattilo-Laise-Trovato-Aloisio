import {Component, Input, OnInit} from '@angular/core';
import {switchMap} from "rxjs";
import {InsertionService} from "../../../../service/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../../../service/user.service";
import {PageBasicInsertionDto} from "../../../../Model/pageBasicInsertionDto";
import {UserDto} from "../../../../Model/userDto";
;


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  @Input() user: UserDto | undefined;
  userInsertion: PageBasicInsertionDto | undefined;
  page= 0

  id: number | undefined;
  modalOpen = false;
  modalImage: string | undefined;

  constructor(
    private insertionService: InsertionService,
    private route: ActivatedRoute,
    private userService: UserService
  ) {}


  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap((params) => {
        this.id = Number(params.get('userid'));
        return this.userService.getById(this.id);
      })
    ).subscribe(
      (user: UserDto) => {
        this.user = user;
        console.log(this.user)
        this.insertionService.getInsertionByUserId(this.id!, this.page).subscribe(
          (data: PageBasicInsertionDto) => {
            this.userInsertion = data;
            console.log(data)
            //this.processImages(data.content);
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
  }

/*
  loadmore() {
    this.page += 1;
    this.userService.getAllInsertionsByUser(this.id,this.page).subscribe((insertions: PageBasicInsertionDto) => {
      // Aggiungo nuovi prodotti alla lista esistente invece di sostituirla
      this.userInsertion?.content?.push(...insertions.content);
      this.processImages(insertions.content);

    });
  }
*/

}
