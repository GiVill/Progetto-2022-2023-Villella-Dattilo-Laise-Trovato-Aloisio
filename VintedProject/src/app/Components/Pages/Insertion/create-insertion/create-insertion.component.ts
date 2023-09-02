import {Component, OnInit} from '@angular/core';
import {InsertionService} from "../../../../api/insertion.service";
import {Router} from "@angular/router";
import {CookiesService} from "../../../../api/cookies.service";
import {BasicInsertionDto} from "../../../../model/basicInsertionDto";
import {MatSnackBar} from "@angular/material/snack-bar";
import {V1InsertionsBody} from "../../../../model/v1InsertionsBody";


@Component({
  selector: 'app-create-insertion',
  templateUrl: './create-insertion.component.html',
  styleUrls: ['./create-insertion.component.css']
})
export class CreateInsertionComponent implements OnInit{
  categoryOptions: BasicInsertionDto.CategoryEnum[] = Object.values(BasicInsertionDto.CategoryEnum);
  brandOptions: BasicInsertionDto.BrandEnum[] = Object.values(BasicInsertionDto.BrandEnum);
  image !: File
  inserzione : BasicInsertionDto = {
    description: "", price: 0, title: "", isPrivate:false, available:true,  userId: Number(this.cookieService.getUserId()),
  };

  constructor(private router: Router,
              private insertionService: InsertionService,
              private snackBar: MatSnackBar,
              private cookieService: CookiesService)
                                                    { }

  caricaFoto(event: any) {
    const fileInput = event.target;
    const files = fileInput.files;
    this.image = files[0]
  }
  controllo(){
    if (!this.inserzione.title) {
      this.snackBar.open("Devi inserire un titolo all' inserzione", 'OK');
      return false}
    if (!this.inserzione.category) {
      this.snackBar.open("Devi aggiungere una categoria all' inserzione", 'OK');
      return false}
    if (!this.inserzione.brand) {
      this.snackBar.open("Devi aggiungere una categoria all' inserzione", 'OK');
      return false}
    if (!this.inserzione.price){
      this.snackBar.open("Inserire un prezzo all' inserzione", 'OK');
      return false}
    return true
  }

  formValido() {
    return (
      this.inserzione.title &&
      this.inserzione.category &&
      this.inserzione.brand &&
      this.inserzione.price > 0
    );
  }


  creaInserzione() {
    if( this.controllo()){
      console.log(this.inserzione)
      console.log(this.image)
      console.log(this.inserzione)
      this.insertionService.addInsertion(this.inserzione, this.image).subscribe(

        response => {
          console.log(this.inserzione)
          console.log("OK => ",response)
          //TODO
          this.snackBar.open("Inserzione creata con successo", 'OK');
          this.router.navigate(['/']);
        },
        error => {
          console.log("ERRORE REGISTRAZIONE INSERZIONE => ",error)
          console.log(this.inserzione)
          this.snackBar.open("Errore. Riprovare.", 'OK');
          //TODO
        }
      );

    }
  }

  ngOnInit(): void {
    if (!this.cookieService.checkUserToken()){
      this.router.navigate(['/login']);
    }
  }


  privatez() {
    this.inserzione.isPrivate=!this.inserzione.isPrivate
  }
}
