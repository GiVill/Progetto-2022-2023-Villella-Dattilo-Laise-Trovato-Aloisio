import {PageBasicInsertionDto} from "../../../model/pageBasicInsertionDto";
import {UserDto} from "../../../model/userDto";
import {UserService} from "../../../api/user.service";
import {Component, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {InsertionService} from "../../../api/insertion.service";
import {CookieService} from "ngx-cookie-service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit{
  users: UserDto[] = [];
  category: string | undefined;
  pageNumber: number = 0;
  insertion: PageBasicInsertionDto | undefined;
  disableLoadMore= false;


  constructor(private http: HttpClient,
              private insertionService: InsertionService,
              private cookieService: CookieService,
              private route: ActivatedRoute,
              private userService: UserService

  ){}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.category = String(params.get('categoryName'));
      this.getProductsByCategory();
    });
  }


  public getProductsByCategory() {
    if (!this.category || this.category.trim() === '') {
      console.log("Non dovresti essere qui...")
    }

    this.insertionService.getByCategory(this.category!,this.pageNumber).subscribe((insertions: PageBasicInsertionDto) => {
      this.insertion = insertions;

      const userIds = this.insertion.content?.map((insertion) => insertion.userId).filter((id, index, array) => array.indexOf(id) === index);

      if (userIds) {
        // Fetch all users by their IDs and store them in the users array
        userIds.forEach((userId) => {
          this.userService.getUserDtoById(userId).subscribe((user: UserDto) => {
            this.users.push(user);
          });
        });
      }
    });

  }

  getUserByUserId(userId: number): UserDto  {
    return <UserDto>this.users.find(user => user.id === userId);
  }

}
