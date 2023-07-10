import {Component, OnInit} from '@angular/core';
import {InsertionService} from "../../../../service/insertion.service";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {CookiesService} from "../../../../service/cookies.service";
import {BasicInsertionDto, Brand, Category} from "../../../../Model/basicInsertionDto";

@Component({
  selector: 'app-create-insertion',
  templateUrl: './create-insertion.component.html',
  styleUrls: ['./create-insertion.component.css']
})
export class CreateInsertionComponent implements OnInit{
  image !: File
  inserzione : BasicInsertionDto = {
    id: 0,
    title: "sfdfsd",
    price: 213,
    description: "dasd",
    condition: "",
    creationDate: "2023-07-10",
    isPrivate: true,
    endDate: "2023-07-10",
    imageName: "",
    brand: "Adidas",
    category: "ABBIGLIAMENTO",
    userId: 2
  };

  categoryOptions: Category.CategoryEnum[] = Object.values(Category.CategoryEnum);
  brandOptions: Brand.BrandEnum[] = Object.values(Brand.BrandEnum);

  constructor(private router: Router,
              private insertionService: InsertionService,
              private cookieService: CookiesService)
                                                    { }

  caricaFoto(event: any) {
    const fileInput = event.target;
    const files = fileInput.files;

    // Limita il numero di foto a 6
    if (files.length > 6) {
      alert('Puoi caricare al massimo 6 foto.');
      return;
    }

    // Limita la dimensione delle foto a 1 MB ciascuna
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      if (file.size > 1024 * 1024) {
        alert('Le foto devono avere una dimensione massima di 1 MB ciascuna.');
        return;
      }
    }

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
    this.insertionService.addInsertion(this.inserzione, this.image).subscribe(


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
