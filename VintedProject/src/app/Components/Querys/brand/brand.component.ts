import {Component, OnInit} from '@angular/core';
import {Page} from "../../../Model/page.model";
import {BasicInsertion} from "../../../Model/basic-insertion.model";
import {InsertionService} from "../../../service/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {ImageService} from "../../../service/image.service";

@Component({
  selector: 'app-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.css']
})
export class BrandComponent implements OnInit{
  Brandedinsertion: Page<BasicInsertion> | undefined;
  page = 1;
  brandName: string | undefined;

  constructor(private insertionService: InsertionService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.brandName = String(params.get('brandName'));});

    this.insertionService.getInsertionByBrand(this.brandName,this.page).subscribe((insertions: Page<BasicInsertion>) => {
      this.Brandedinsertion = insertions;
      this.processImages();
    });
  }


  processImages(): void {
    this.Brandedinsertion?.content.forEach(async (insertion: BasicInsertion) => {
      insertion.imageSrc = await ImageService.setProductImageSrc(insertion.image);
    });
  }


}
