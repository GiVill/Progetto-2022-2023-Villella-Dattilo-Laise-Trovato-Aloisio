import {Component, Input, OnInit} from '@angular/core';
import {BasicInsertion} from "../../Model/basic-insertion.model";
import {User} from "../../Model/user.model";
import {Observable, switchMap} from "rxjs";
import {Page} from "../../Model/page.model";
import {InsertionService} from "../../service/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../service/user.service";
import {ImageService} from "../../service/image.service";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  @Input() user: User | undefined;
  userInsertion: Page<BasicInsertion> | undefined;
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
        return this.userService.getUserById(this.id);
      })
    ).subscribe(
      (user: User) => {
        this.user = user;
        console.log(this.user)
        this.userService.getAllInsertionsByUser(this.id, this.page).subscribe(
          (data: Page<BasicInsertion>) => {
            this.userInsertion = data;
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


  loadmore() {
    this.page += 1;
    this.userService.getAllInsertionsByUser(this.id,this.page).subscribe((insertions: Page<BasicInsertion>) => {
      // Aggiungo nuovi prodotti alla lista esistente invece di sostituirla
      this.userInsertion?.content.push(...insertions.content);
      this.processImages(insertions.content);

    });
  }

  processImages(insertions: BasicInsertion[]): void {
    insertions.forEach(async (insertion: BasicInsertion) => {
      insertion.imageSrc = await ImageService.setProductImageSrc(insertion.image);
    });
  }



}
