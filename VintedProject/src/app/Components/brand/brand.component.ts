import {Component, OnInit} from '@angular/core';
import {Page} from "../../Model/page.model";
import {BasicInsertion} from "../../Model/basic-insertion.model";
import {InsertionService} from "../../service/insertion.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.css']
})
export class BrandComponent implements OnInit{
  BrandedInserction: Page<BasicInsertion> | undefined;
  page = 1;
  brandName: string | undefined;

  constructor(private insertionService: InsertionService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.brandName = String(params.get('brandName'));});

    this.insertionService.getInsertionByBrand(this.brandName,this.page).subscribe((insertions: Page<BasicInsertion>) => {
      this.BrandedInserction = insertions;
    });
  }


}
