import {Component, OnInit} from '@angular/core';
import {Page} from "../../Model/page.model";
import {BasicInsertion} from "../../Model/basic-insertion.model";
import {InsertionService} from "../../service/insertion.service";
import {ImageService} from "../../service/image.service";

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {
  catalog: Page<BasicInsertion> | undefined;
  page = 0;


  constructor(private insertionService: InsertionService) {}

  ngOnInit(): void {
    this.loadInsertions();
  }

  loadInsertions(): void {
    this.insertionService.getAllInsertions(this.page).subscribe((insertions: Page<BasicInsertion>) => {
      this.catalog = insertions;
      this.processImages(insertions.content);
      console.log(this.catalog);
    });
  }

  loadmore() {
    this.page += 1;
    this.insertionService.getAllInsertions(this.page).subscribe((insertions: Page<BasicInsertion>) => {
      // Aggiungi i nuovi prodotti alla lista esistente invece di sostituirla
      this.catalog?.content.push(...insertions.content);
      this.processImages(insertions.content);
      console.log(this.catalog);
    });
  }


  processImages(insertions: BasicInsertion[]): void {
    insertions.forEach(async (insertion: BasicInsertion) => {
      insertion.imageSrc = await ImageService.setProductImageSrc(insertion.image);
    });
  }


}

