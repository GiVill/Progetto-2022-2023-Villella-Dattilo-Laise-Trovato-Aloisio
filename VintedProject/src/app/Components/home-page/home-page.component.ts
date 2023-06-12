import {Component, Input, OnInit} from '@angular/core';
import {BasicInsertion} from "../../Model/basic-insertion.model";
import {InsertionService} from "../../service/insertion.service";
import {Page} from "../../Model/page.model";
import {ImageService} from "../../service/image.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  mostRequested: Page<BasicInsertion> | undefined;
  feed: Page<BasicInsertion> | undefined;
  page = 1;

  constructor(private insertionService: InsertionService) { }

  ngOnInit(): void {
    this.insertionService.getAllInsertions(this.page).subscribe((insertions: Page<BasicInsertion>) => {
      this.mostRequested = {
        ...insertions,
        content: insertions.content.slice(0, 5)
      };

        this.insertionService.getAllInsertions(this.page).subscribe((insertions: Page<BasicInsertion>) => {
          this.feed = insertions;
        });

      this.processImages();
    });
  }

  processImages(): void {
    this.mostRequested?.content.forEach(async (insertion: BasicInsertion) => {
      insertion.imageSrc = await ImageService.setProductImageSrc(insertion.image);
    });
    this.feed?.content.forEach(async (insertion: BasicInsertion) => {
      insertion.imageSrc = await ImageService.setProductImageSrc(insertion.image);
    });
  }
}

