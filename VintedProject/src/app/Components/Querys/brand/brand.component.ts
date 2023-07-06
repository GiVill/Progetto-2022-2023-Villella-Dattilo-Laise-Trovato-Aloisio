import {Component, OnInit} from '@angular/core';
import {BasicInsertionDto} from "../../../Model/basicInsertionDto";
import {PageBasicInsertionDto} from "../../../Model/pageBasicInsertionDto";
import {InsertionService} from "../../../service/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {ImageService} from "../../../service/image.service";

@Component({
  selector: 'app-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.css']
})
export class BrandComponent implements OnInit{
  Brandedinsertion: PageBasicInsertionDto | undefined;
  page = 1;
  brandName: string | undefined;

  constructor(private insertionService: InsertionService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.brandName = String(params.get('brandName'));});

    this.insertionService.getInsertionByBrand(this.brandName,this.page).subscribe((insertions: PageBasicInsertionDto) => {
      this.Brandedinsertion = insertions;
      this.processImages();
    });
  }


  processImages(): void {
    this.Brandedinsertion?.content?.forEach(async (insertion: BasicInsertionDto) => {
      insertion.imagePath = await ImageService.setProductImageSrc(insertion.image);
    });
  }



}
