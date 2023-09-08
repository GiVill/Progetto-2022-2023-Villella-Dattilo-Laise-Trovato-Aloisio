import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';


@Injectable({
  providedIn: 'root'
})
export class TokenService {


  constructor(private jwtHelper: JwtHelperService) { }

  getUserStringFromToken(token: string) {
    const decodedToken = this.jwtHelper.decodeToken(token);
    const userId = decodedToken?.sub;
    if (userId) {
      return userId;
    }
    return null
  }
}
