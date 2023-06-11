import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {ActivatedRoute} from "@angular/router";
import {BasicInsertion} from "../../Model/basic-insertion.model";
import {InsertionService} from "../../service/insertion.service";
import {of, tap} from "rxjs";
import {ImageService} from "../../service/image.service";
import {Page} from "../../Model/page.model";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  search: string | undefined;
  pageNumber: number = 0;
  insertion: Page<BasicInsertion> | undefined;
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

    return this.insertionService.findAllByTitleLike(pageNumber, this.search).pipe(
      tap((insertions: Page<BasicInsertion>) => {
        this.insertion = insertions;
        this.processImages();
      }))
  }


  processImages(): void {
    this.insertion?.content.forEach(async (insertion: BasicInsertion) => {
      insertion.imageSrc = await ImageService.setProductImageSrc(insertion.image);
    });
  }
}
