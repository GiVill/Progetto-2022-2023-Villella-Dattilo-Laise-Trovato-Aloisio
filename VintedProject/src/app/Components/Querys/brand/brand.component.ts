import {Component, OnInit} from '@angular/core';

import {InsertionService} from "../../../service/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {PageBasicInsertionDto} from "../../../model/pageBasicInsertionDto";


@Component({
  selector: 'app-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.css']
})
export class BrandComponent implements OnInit{
  Brandedinsertion: PageBasicInsertionDto | undefined;
  page = 0;
  brandName: string | undefined;

  constructor(private insertionService: InsertionService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.brandName = String(params.get('brandName'));});

    this.insertionService.getByBrand(this.brandName!,this.page).subscribe((insertions: PageBasicInsertionDto) => {
      this.Brandedinsertion = insertions;
    });
  }

}
