import { Component } from '@angular/core';
import {NewUser} from "../../../../Model/new-user.model";
import {Observable} from "rxjs";
import {AuthService} from "../../../../service/auth.service";
import {InsertionService} from "../../../../service/insertion.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-insertion',
  templateUrl: './create-insertion.component.html',
  styleUrls: ['./create-insertion.component.css']
})
export class CreateInsertionComponent {
  inserzione = {
    titolo: '',
    descrizione: '',
    prezzo: 0,
    foto: []
  };

  constructor(private router: Router,
              private insertionService: InsertionService) { }

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

    // Salva le foto nell'oggetto inserzione
    this.inserzione.foto = Array.from(files);
  }

  formValido() {
    return (
      this.inserzione.titolo &&
      this.inserzione.descrizione &&
      this.inserzione.prezzo > 0 &&
      this.inserzione.foto.length > 0
    );
  }

  creaInserzione() {
    this.insertionService.addInsertion(this.inserzione).subscribe(
      response => {
        console.log("OK => ",response)
        // Handle success
        this.router.navigate(['/']);
      },
      error => {
        console.log("ERRORE REGISTRAZIONE => ",error)
        // Handle error
      }
    );

  }



}
