# VintedProject - Enterprise Applications

## Informazioni Importanti

#### Back-end

Prima di avviare il progetto:

1. Scaricare ed avviare Keycloak.
   - Utilizzare l'immagine Docker disponibile su Docker Hub: `docker pull firstorm9/keycloak_dev:latest`.
   - Avviare sulla porta `8090`

2. Avviare il database Postgres. Nel progetto VintedProject (back-end), troverai il file *docker-compose.yml*. Eseguire il comando `docker-compose up` oppure utilizzare eventuali plugin

3. Avviare il progetto principale. Dopo averlo completamente avviato commentare la linea `createDB` (verso la fine della classe `DbGenerator`).

#### Angular

Per il progetto Angular:

1. Installare le dipendenze npm:
   - Tramite `npm install`. Eventualmente utlizzare `--force` in caso d'errore

#### Android

Per il progetto Android:

1. Installare le dipendeze gradle

2. Impostare il corretto path per l'SDK (può essere modificato nel local.properties)

## Informazioni Genenerali:

Nella cartella VintedProject è presente il progetto Back-end in Java

Nella cartella VintedProjectAngular è presente il progetto Front-end per l'applicazione Web

Nella cartella VintedAndroid è presente il progetto Front-end per Android
