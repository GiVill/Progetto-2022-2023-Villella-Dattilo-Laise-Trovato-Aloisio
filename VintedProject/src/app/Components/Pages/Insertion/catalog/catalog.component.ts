import {Component, OnInit} from '@angular/core';
import {InsertionService} from "../../../../service/insertion.service";
import {PageBasicInsertionDto} from "../../../../model/pageBasicInsertionDto";



@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {
  catalog: PageBasicInsertionDto | undefined;
  page = 0;


  constructor(private insertionService: InsertionService) {}

  ngOnInit(): void {
    this.loadInsertions();
  }

  loadInsertions(): void {
    this.insertionService.all4(this.page).subscribe((insertions: PageBasicInsertionDto) => {
      this.catalog = insertions;
      console.log(this.catalog);
    });
  }

  loadmore() {
    this.page += 1;
    this.insertionService.all4(this.page).subscribe((insertions: PageBasicInsertionDto) => {
      // Aggiungi i nuovi prodotti alla lista esistente invece di sostituirla
      if (this.catalog?.content && insertions.content) {
        this.catalog.content.push(...insertions.content);
        console.log(this.catalog);
      }
    });
  }


}

