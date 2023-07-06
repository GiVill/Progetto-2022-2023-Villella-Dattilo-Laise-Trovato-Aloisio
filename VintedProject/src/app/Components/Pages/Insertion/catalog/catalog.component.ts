import {Component, OnInit} from '@angular/core';
import {BasicInsertionDto} from "../../../../Model/basicInsertionDto";
import {PageBasicInsertionDto} from "../../../../Model/pageBasicInsertionDto";
import {InsertionService} from "../../../../service/insertion.service";
import {ImageService} from "../../../../service/image.service";


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
    this.insertionService.getAllInsertions(this.page).subscribe((insertions: PageBasicInsertionDto) => {
      this.catalog = insertions;
      this.processImages(insertions.content);
      console.log(this.catalog);
    });
  }

  loadmore() {
    this.page += 1;
    this.insertionService.getAllInsertions(this.page).subscribe((insertions: PageBasicInsertionDto) => {
      // Aggiungi i nuovi prodotti alla lista esistente invece di sostituirla
      if (this.catalog?.content && insertions.content) {
        this.catalog.content.push(...insertions.content);
        this.processImages(insertions.content);
        console.log(this.catalog);
      }
    });
  }


  processImages(insertions: Array<BasicInsertionDto> | undefined): void {
    insertions?.forEach(async (insertion: BasicInsertionDto) => {
      insertion.imagePath = await ImageService.setProductImageSrc(insertion.image);
    });
  }


}

