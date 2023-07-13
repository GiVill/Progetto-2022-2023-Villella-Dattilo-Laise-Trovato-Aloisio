import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {InsertionService} from "../../../service/insertion.service";
import {CookieService} from "ngx-cookie-service";
import {ActivatedRoute} from "@angular/router";

import {of, tap} from "rxjs";
import {PageBasicInsertionDto} from "../../../Model/pageBasicInsertionDto";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit{

  category: string | undefined;
  pageNumber: number = 0;
  insertion: PageBasicInsertionDto | undefined;
  disableLoadMore= false;


  constructor(private http: HttpClient,
              private insertionService: InsertionService,
              private cookieService: CookieService,
              private route: ActivatedRoute,

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
      })
    );
  }

}
