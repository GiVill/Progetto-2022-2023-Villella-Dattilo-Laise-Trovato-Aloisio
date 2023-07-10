import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {ActivatedRoute} from "@angular/router";
import {PageBasicInsertionDto} from "../../../Model/pageBasicInsertionDto";
import {BasicInsertionDto} from "../../../Model/basicInsertionDto";
import {InsertionService} from "../../../service/insertion.service";
import {of, tap} from "rxjs";
import {ImageService} from "../../../service/image.service";


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  search: string | undefined;
  pageNumber: number = 0;
  insertion: PageBasicInsertionDto | undefined;
  disableLoadMore= false;


  constructor(private http: HttpClient,
              private insertionService: InsertionService,
              private cookieService: CookieService,
              private route: ActivatedRoute,
              private imageService: ImageService,

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

    return this.insertionService.findAllByTitleLike(pageNumber, this.search).subscribe(
      (insertions: PageBasicInsertionDto) => {
        this.insertion = insertions;
      }
    );
  }

}
