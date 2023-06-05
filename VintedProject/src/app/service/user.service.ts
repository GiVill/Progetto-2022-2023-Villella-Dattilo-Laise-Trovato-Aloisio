import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../Model/user.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = '/vintedProject-api/v1/users';

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}`);
  }

  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${userId}`);
  }

  deleteUserById(userId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${userId}`, { responseType: 'text' });
  }
}
