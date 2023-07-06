import {Component, OnInit} from '@angular/core';
import {Page} from "../../../Model/page.model";
import {BasicInsertion} from "../../../Model/basic-insertion.model";
import {HttpClient} from "@angular/common/http";
import {InsertionService} from "../../../service/insertion.service";
import {CookieService} from "ngx-cookie-service";
import {ActivatedRoute} from "@angular/router";
import {ImageService} from "../../../service/image.service";
import {of, tap} from "rxjs";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit{

  category: string | undefined;
  pageNumber: number = 0;
  insertion: Page<BasicInsertion> | undefined;
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

    return this.insertionService.findAllByCategory(pageNumber, this.category).pipe(
      tap((insertions: Page<BasicInsertion>) => {
        this.insertion = insertions;
        this.processImages();
      })
    );
  }

  processImages(): void {
    this.insertion?.content.forEach(async (insertion: BasicInsertion) => {
      insertion.imageSrc = await ImageService.setProductImageSrc(insertion.image);
    });
  }


}