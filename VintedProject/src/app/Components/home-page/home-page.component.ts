import {Component, Input, OnInit} from '@angular/core';
import {ProductCardComponent} from "../product-card/product-card.component";
import {BasicInsertion} from "../../Model/basic-insertion.model";
import {InsertionService} from "../../service/insertion.service";
import {Page} from "../../Model/page.model";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  insertion: Page<BasicInsertion> | undefined;
  page = 1;

  constructor(private insertionService: InsertionService) { }

  ngOnInit(): void {
    this.insertionService.getAllInsertions(this.page).subscribe((insertions: Page<BasicInsertion>) => {
      this.insertion = insertions;
    });
  }
}

