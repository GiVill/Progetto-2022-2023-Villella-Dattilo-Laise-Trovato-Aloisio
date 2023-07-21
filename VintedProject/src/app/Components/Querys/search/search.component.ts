import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {ActivatedRoute} from "@angular/router";
import {InsertionService} from "../../../service/insertion.service";
import {of, tap} from "rxjs";
import {ImageService} from "../../../service/image.service";
import {PageBasicInsertionDto} from "../../../Model/pageBasicInsertionDto";
import {UserDto} from "../../../Model/userDto";
import {UserService} from "../../../service/user.service";


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  users: UserDto[] = [];
  search: string | undefined;
  pageNumber: number = 0;
  insertion!: PageBasicInsertionDto;
  disableLoadMore= false;


  constructor(private http: HttpClient,
              private insertionService: InsertionService,
              private cookieService: CookieService,
              private route: ActivatedRoute,
              private userService: UserService

             ){}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.search = String(params.get('searchstring'));
      this.getProductsByTitleLike(0);
    });
  }


  public getProductsByTitleLike(pageNumber: number) {
    if (!this.search || this.search.trim() === '') {
      return of(undefined); // Restituisci un observable di undefined
    }

    return this.insertionService.getByTitle( this.search, pageNumber).subscribe(
      (insertions: PageBasicInsertionDto) => {
        this.insertion = insertions;

        const userIds =  this.insertion.content!.map((insertion) => insertion.userId).filter((id, index, array) => array.indexOf(id) === index);
        userIds.forEach((userId) => {
          this.userService.getById(userId).subscribe((user: UserDto) => {
            this.users.push(user);
          });
        });
  })}

  getUserByUserId(userId: number): UserDto  {
    return <UserDto>this.users.find(user => user.id === userId);
  }

}
