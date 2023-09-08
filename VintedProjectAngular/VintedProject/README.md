## Disposizione dei File:

scr/app/api: Contiene tutti i services <br>
scr/app/Components: Contiene tutti componenti<br>
scr/app/model: Contiene tutti i Dto<br>


Dentro Components troviamo:<br>
    Components: Contiene tutte le componenti più piccole dell'interfaccia come le Cards.<br>
    home-page: La pagina principale.<br>
    Pages: Le componenti più grandi come il carrello, la chat, le inserzioni.<br>
    Querys: Sono pagine molto simili dove cambia il caricamento dei components.<br>

Dentro Pages abbiamo la logica in questo modo:<br>
    Dentro User troviamo tutto quello che riguarda l'utente come per esempio il login, il profilo.<br>
    Dentro Insertion troviamo tutto quello che riguarda le inserzioni come per esempio la pagina delle inserzioni, la creazione dell'inerzione o il catalog che fa vedere tutte le inserzioni.<br>
    

## Design

Desig della homepage:
![HOME](/src/assets/resources/samples/Homepage1.png)
![HOME](/src/assets/resources/samples/Homepage2.png)
![HOME](/src/assets/resources/samples/Homepage3.png)


Desig della pagina dove viene create un inserzione:
![HOME](/src/assets/resources/samples/Crea%20Inserzione.png)

Desig della pagina della registrazione:
![HOME](/src/assets/resources/samples/Registrazione.png)

Desig della pagina del login:
![HOME](/src/assets/resources/samples/Login.png)

Desig del mio profilo (con visualizzazione delle inserzioni private):
![HOME](/src/assets/resources/samples/capability.png)
![HOME](/src/assets/resources/samples/Mio%20profilo2.png)


Design delle pagini delle inserzioni(anche private)
![HOME](/src/assets/resources/samples/Private.png)


Desig della pagina del profilo degli altri utenti:
![HOME](/src/assets/resources/samples/Profilo%20Altri%20Utenti.png)


E tanto altro. Basta ricordarsi di usare --force qunado siesegue npm install.


## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.


## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.
