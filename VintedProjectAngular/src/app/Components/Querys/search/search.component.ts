import {Component, OnInit} from "@angular/core";
import {UserDto} from "../../../model/userDto";
import {PageBasicInsertionDto} from "../../../model/pageBasicInsertionDto";
import {HttpClient} from "@angular/common/http";
import {InsertionService} from "../../../api/insertion.service";
import {CookieService} from "ngx-cookie-service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../../api/user.service";
import {of} from "rxjs";


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
          this.userService.getUserDtoById(userId).subscribe((user: UserDto) => {
            this.users.push(user);
          });
        });
  })}

  getUserByUserId(userId: number): UserDto  {
    return <UserDto>this.users.find(user => user.id === userId);
  }

}
