import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {NewUser} from "../Model/new-user.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private apiUrl = 'https://localhost:8010/vintedProject-api/v1';

  constructor(private http: HttpClient) { }

  signUp(newUser: NewUser): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/sign-up`, newUser);
  }

  login(email: string, password: string): Observable<boolean> {
    const body = { email, password };
    return this.http.post<boolean>(`${this.apiUrl}/login`, body);
  }
}
