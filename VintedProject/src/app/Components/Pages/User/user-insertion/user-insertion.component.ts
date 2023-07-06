import {Component, Input, OnInit} from '@angular/core';
import {UserDto} from "../../../../Model/userDto";
import {BasicInsertionDto} from "../../../../Model/basicInsertionDto";
import {ActivatedRoute} from "@angular/router";
import {InsertionService} from "../../../../service/insertion.service";
import {UserService} from "../../../../service/user.service";
import {ImageService} from "../../../../service/image.service";

@Component({
  selector: 'app-user-insertion',
  templateUrl: './user-insertion.component.html',
  styleUrls: ['./user-insertion.component.css']
})
export class UserInsertionComponent implements OnInit{

  @Input() user: UserDto | undefined;
  @Input() insertion: BasicInsertionDto | undefined;


  constructor(private insertionService: InsertionService,
              private route: ActivatedRoute,
              private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getUserById(this.user?.id).subscribe(
      (data: UserDto) => {
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
        this.user.imagepath = imageSrc;
      }
    }
  }


}
