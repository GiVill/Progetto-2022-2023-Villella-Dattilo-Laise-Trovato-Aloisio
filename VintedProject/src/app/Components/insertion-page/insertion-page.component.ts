import {Component, OnInit} from '@angular/core';
import {BasicInsertion} from "../../Model/basic-insertion.model";
import {InsertionService} from "../../service/insertion.service";
import {Page} from "../../Model/page.model";
import {ImageService} from "../../service/image.service";
import {ActivatedRoute} from "@angular/router";
import {User} from "../../Model/user.model";
import {UserService} from "../../service/user.service";
import {Observable} from "rxjs";


@Component({
  selector: 'app-insertion-page',
  templateUrl: './insertion-page.component.html',
  styleUrls: ['./insertion-page.component.css']
})

export class InsertionPageComponent implements OnInit {
  insertion: BasicInsertion | undefined;
  user: Observable<User> | undefined;
  page: 1 | undefined;
  userOtherInsertion: Page<BasicInsertion> | undefined;
  id: number | undefined;
  modalOpen = false;
  modalImage: string | undefined;

  constructor(
    private insertionService: InsertionService,
    private route: ActivatedRoute,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.id = Number(params.get('id'));
      this.user = this.userService.getUserById(this.insertion?.user?.id);
    });

    this.insertionService.getInsertionById(this.id).subscribe(
      (data: BasicInsertion) => {
        this.insertion = data;
        if (this.insertion?.user) {
          this.user = this.userService.getUserById(this.insertion.user.id);
        }
        this.processImages();
      },
      (error) => {
        console.log('Si è verificato un errore durante il recupero dell\'inserzione:', error);
      }
    );
  }

  async processImages(): Promise<void> {
    if (this.insertion) {
      const imageSrc = await ImageService.setProductImageSrc(this.insertion.image);
      if (imageSrc) {
        this.insertion = { ...this.insertion, imageSrc };
      }
    }
  }

  openModal(imageSrc: string | undefined): void {
    this.modalOpen = true;
    this.modalImage = imageSrc;
  }

  closeModal(): void {
    this.modalOpen = false;
    this.modalImage = undefined;
  }
}



