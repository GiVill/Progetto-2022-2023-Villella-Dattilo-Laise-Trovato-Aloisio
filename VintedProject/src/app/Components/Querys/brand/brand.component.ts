import {Component, OnInit} from '@angular/core';
import {InsertionService} from "../../../service/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {PageBasicInsertionDto} from "../../../Model/pageBasicInsertionDto";
import {UserDto} from "../../../Model/userDto";
import {UserService} from "../../../service/user.service";


@Component({
  selector: 'app-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.css']
})
export class BrandComponent implements OnInit{
  users: UserDto[] = [];
  Brandedinsertion!: PageBasicInsertionDto;
  page = 0;
  brandName: string | undefined;

  constructor(private insertionService: InsertionService,
              private userService: UserService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.brandName = String(params.get('brandName'));});

    this.insertionService.getByBrand(this.brandName!,this.page).subscribe((insertions: PageBasicInsertionDto) => {
      this.Brandedinsertion = insertions;

      const userIds = this.Brandedinsertion.content?.map((insertion) => insertion.userId).filter((id, index, array) => array.indexOf(id) === index);

      if (userIds) {
        // Fetch all users by their IDs and store them in the users array
        userIds.forEach((userId) => {
          this.userService.getById(userId).subscribe((user: UserDto) => {
            this.users.push(user);
          });
        });
      }
    });

    console.log(this.Brandedinsertion)
  }

  getUserByUserId(userId: number): UserDto  {
    return <UserDto>this.users.find(user => user.id === userId);
  }

}