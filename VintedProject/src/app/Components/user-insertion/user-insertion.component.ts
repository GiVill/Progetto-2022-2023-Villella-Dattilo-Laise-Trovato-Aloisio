import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../Model/user.model";
import {InsertionService} from "../../service/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../service/user.service";
import {BasicInsertion} from "../../Model/basic-insertion.model";
import {ImageService} from "../../service/image.service";
import {Page} from "../../Model/page.model";

@Component({
  selector: 'app-user-insertion',
  templateUrl: './user-insertion.component.html',
  styleUrls: ['./user-insertion.component.css']
})
export class UserInsertionComponent implements OnInit{

  @Input() user: User | undefined;
  @Input() insertion: BasicInsertion | undefined;


  constructor(private insertionService: InsertionService,
              private route: ActivatedRoute,
              private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getUserById(this.user?.id).subscribe(
      (data: User) => {
        this.user = data;
        this.processImages();
      },
      (error) => {
        console.log('Si è verificato un errore durante il recupero dell\'inserzione:', error);
      }
    );
  }

  async processImages(): Promise<void> {
    if (this.user && this.user.photo) {
      const imageSrc = await ImageService.setProductImageSrc(this.user.photo);
      if (imageSrc) {
        this.user.photoSrc = imageSrc;
      }
    }
  }


}
