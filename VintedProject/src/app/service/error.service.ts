import { Injectable } from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class ErrorService {

  constructor( private router: Router,
                                      ){ }

    redirectToErrorPage(error) {
      if (error.status === 404) {
        // Redirect to 404 page
        this.router.navigate(['/404']);
        return true
      } else if (error.status === 400) {
        // Redirect to 400 page
        this.router.navigate(['/400']);
        return true
      } else if (error.status === 0 && error.statusText === 'Unknown Error') {
        // Redirect to 400 page
        this.router.navigate(['/refused']);
        return true
      } else if (error.status === 403) {
        // Redirect to 403 page
        this.router.navigate(['/403']);
        return true
      }
      return false
    }

}
