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
export class CatalogComponent implements OnInit{

  catalog: Page<BasicInsertion> | undefined;
  page = 1;

  constructor(private insertionService: InsertionService) { }

  ngOnInit(): void {
    this.insertionService.getAllInsertions(this.page).subscribe((insertions: Page<BasicInsertion>) => {
      this.catalog = insertions;
      this.processImages();
    });
  }


  processImages(): void {
    this.catalog?.content.forEach(async (insertion: BasicInsertion) => {
      insertion.imageSrc = await ImageService.setProductImageSrc(insertion.image);
    });
  }
}

