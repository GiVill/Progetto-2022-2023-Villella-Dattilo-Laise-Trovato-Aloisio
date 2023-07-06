import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {NewUserDto} from "../Model/newUserDto";
import {TokenResponse} from "../Model/token-response";

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private apiUrl = 'https://localhost:8010/vintedProject-api/v1';

  constructor(private http: HttpClient) { }

  signUp(newUser: NewUserDto): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/sign-up`, newUser);
  }

  login(email: string, password: string): Observable<TokenResponse> {
    const body = { email, password };
    return this.http.post<TokenResponse>(`${this.apiUrl}/login`, body);
  }
}
