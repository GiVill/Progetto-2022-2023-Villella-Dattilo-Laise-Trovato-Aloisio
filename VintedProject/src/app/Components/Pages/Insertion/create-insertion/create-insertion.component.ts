import {Component, OnInit} from '@angular/core';
import {InsertionService} from "../../../../service/insertion.service";
import {Router} from "@angular/router";
import {CookiesService} from "../../../../service/cookies.service";

import {Brand, Category} from "../../../../model/newInsertionDto";
import {BasicInsertionDto} from "../../../../model/basicInsertionDto";
import {V1InsertionsBody} from "../../../../model/v1InsertionsBody";
import {CookieService} from "ngx-cookie-service";


@Component({
  selector: 'app-create-insertion',
  templateUrl: './create-insertion.component.html',
  styleUrls: ['./create-insertion.component.css']
})
export class CreateInsertionComponent implements OnInit{
  image !: File
  inserzione : BasicInsertionDto = {
    description: "", price: 0, title: "", userId: Number(this.cookieService.getUserId())
  };
  boby: V1InsertionsBody = {insertion:this.inserzione, img:this.image}
  categoryOptions: Category.CategoryEnum[] = Object.values(Category.CategoryEnum);
  brandOptions: Brand.BrandEnum[] = Object.values(Brand.BrandEnum);

  constructor(private router: Router,
              private insertionService: InsertionService,
              private cookieService: CookiesService)
                                                    { }

  caricaFoto(event: any) {
    const fileInput = event.target;
    const files = fileInput.files;

    this.image = files[0]

    // Salva le foto nell'oggetto inserzione
    //this.inserzione.foto = Array.from(files);
  }

  formValido() {
    return (
      this.inserzione.title &&
      this.inserzione.description &&
      this.inserzione.price > 0
      //this.inserzione.foto.length > 0
    );
  }

  creaInserzione() {
    console.log(this.inserzione)
    console.log(this.image)
    this.insertionService.addInsertionProva(this.inserzione, this.image).subscribe(


      response => {
        console.log(this.inserzione)
        console.log("OK => ",response)
        // Handle success
        this.router.navigate(['/']);
      },
      error => {
        console.log("ERRORE REGISTRAZIONE => ",error)
        console.log(this.inserzione)
        // Handle error
      }
    );

  }

  ngOnInit(): void {
    if (!this.cookieService.checkUserToken()){
      this.router.navigate(['/login']);
    }
  }



}
