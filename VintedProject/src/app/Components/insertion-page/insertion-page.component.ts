import {Component, OnInit} from '@angular/core';
import {BasicInsertion} from "../../Model/basic-insertion.model";
import {InsertionService} from "../../service/insertion.service";
import {Page} from "../../Model/page.model";
import {ImageService} from "../../service/image.service";
import {ActivatedRoute} from "@angular/router";


@Component({
  selector: 'app-insertion-page',
  templateUrl: './insertion-page.component.html',
  styleUrls: ['./insertion-page.component.css']
})

export class InsertionPageComponent implements OnInit {
  insertion: BasicInsertion | undefined;
  id: number | undefined;
  modalOpen = false;
  modalImage: string | undefined;

  constructor(private insertionService: InsertionService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.id = Number(params.get('id'));
    });

    this.insertionService.getInsertionById(this.id).subscribe(
      (data: BasicInsertion) => {
        this.insertion = data;
        this.processImages();
      },
      (error) => {
        console.log('Si è verificato un errore durante il recupero dell\'inserzione:', error);
      }
    );
  }

  async processImages(): Promise<void> {
    if (this.insertion) {
      const imageSrc = await ImageService.setProductImageSrc(this.insertion?.image);
      if (imageSrc) {
        this.insertion = { ...this.insertion, imageSrc };
      }
    }
  }

  openModal(imageSrc: string | undefined): void {
    this.modalOpen = true;
    this.modalImage = imageSrc;
  }

  closeModal(): void {
    this.modalOpen = false;
    this.modalImage = undefined;
  }
}




