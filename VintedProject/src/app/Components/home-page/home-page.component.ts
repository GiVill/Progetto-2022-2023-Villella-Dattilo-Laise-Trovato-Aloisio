import {Component, Input, OnInit} from '@angular/core';
import {BasicInsertionDto} from "../../Model/basicInsertionDto";
import {PageBasicInsertionDto} from "../../Model/pageBasicInsertionDto";
import {InsertionService} from "../../service/insertion.service";

import {ImageService} from "../../service/image.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  mostRequested:PageBasicInsertionDto | undefined;
  feed: PageBasicInsertionDto | undefined;
  page = 0;

  constructor(private insertionService: InsertionService) { }

  ngOnInit(): void {
    this.insertionService.getAllInsertions(this.page).subscribe((insertions: PageBasicInsertionDto) => {

      this.mostRequested = {
        ...insertions,
        content: insertions.content?.slice(0, 6)
      };

      this.insertionService.getAllInsertions(this.page).subscribe((insertions: PageBasicInsertionDto) => {
        this.feed = {
          ...insertions,
          content: insertions.content?.slice(0, 6)
        };
      });
    })
  }

  processImages(): void {
    /*
    this.mostRequested.content?.forEach(async (insertion: BasicInsertionDto) => {
      insertion.imageSrc = await ImageService.setProductImageSrc(insertion.image);
    });
    this.feed?.content.forEach(async (insertion: BasicInsertionDto) => {
      insertion.imageSrc = await ImageService.setProductImageSrc(insertion.image);
    });
  }
     */
  }
}

