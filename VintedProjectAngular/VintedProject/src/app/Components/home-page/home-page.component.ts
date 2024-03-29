import {Component, Input, OnInit} from '@angular/core';
import {UserDto} from "../../model/userDto";
import {PageBasicInsertionDto} from "../../model/pageBasicInsertionDto";
import {InsertionService} from "../../api/insertion.service";
import {UserService} from "../../api/user.service";
import {CookieService} from "ngx-cookie-service";



@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  users: UserDto[] = [];
  mostRequested!: PageBasicInsertionDto;
  feed: PageBasicInsertionDto | undefined;
  page = 0;

  constructor(
    private insertionService: InsertionService,
    private userService: UserService,
    private cookieService: CookieService
  ) {}

  ngOnInit(): void {
    this.insertionService.userGetAll(this.page).subscribe(
      (insertions: PageBasicInsertionDto) => {
        this.mostRequested = {
          ...insertions,
          content: insertions.content?.slice(0, 5),
        };
        // Extract all unique user IDs from the insertions
        const userIds = this.mostRequested.content?.map((insertion) => insertion.userId).filter((id, index, array) => array.indexOf(id) === index);

        if (userIds) {
          // Fetch all users by their IDs and store them in the users array
          userIds.forEach((userId) => {
            this.userService.getUserDtoById(userId).subscribe((user: UserDto) => {
              this.users.push(user);
            });
          });
        }

        this.insertionService.userGetAll(this.page).subscribe(
          (insertions: PageBasicInsertionDto) => {
            if (insertions.content && insertions.content.length > 1) {
              this.shuffleArray(insertions.content);
            }
            this.feed = {
              ...insertions,
              content: insertions.content?.slice(0, 6),
            };
          }
        );
      })
  }

  private shuffleArray(array: any[]): void {
      for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
  }


  getUserByUserId(userId: number): UserDto  {
    return <UserDto>this.users.find(user => user.id === userId);
  }
}

