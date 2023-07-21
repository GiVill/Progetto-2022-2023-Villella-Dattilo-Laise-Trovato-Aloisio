import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {InsertionService} from "../../../service/insertion.service";
import {CookieService} from "ngx-cookie-service";
import {ActivatedRoute} from "@angular/router";

import {of, tap} from "rxjs";
import {PageBasicInsertionDto} from "../../../Model/pageBasicInsertionDto";
import {UserDto} from "../../../Model/userDto";
import {UserService} from "../../../service/user.service";

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
      this.getProductsByCategory(0);
    });
  }


  public getProductsByCategory(pageNumber: number) {
    if (!this.category || this.category.trim() === '') {
      return of(undefined); // Restituisci un observable di undefined
    }

    return this.insertionService.getByCategory( this.category, this.pageNumber,).pipe(
      tap((insertions: PageBasicInsertionDto) => {
        this.insertion = insertions;
        const userIds = this.insertion.content?.map((insertion) => insertion.userId).filter((id, index, array) => array.indexOf(id) === index);

        if (userIds) {
          // Fetch all users by their IDs and store them in the users array
          userIds.forEach((userId) => {
            this.userService.getById(userId).subscribe((user: UserDto) => {
              this.users.push(user);
            });
          });
        }
      })
    );
  }

  getUserByUserId(userId: number): UserDto  {
    return <UserDto>this.users.find(user => user.id === userId);
  }

}
