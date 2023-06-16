import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../Model/user.model";
import {Page} from "../Model/page.model";
import {BasicInsertion} from "../Model/basic-insertion.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8010/vintedProject-api/v1/insertions';

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}`);
  }

  getUserById(userId: number | undefined): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${userId}`);
  }

  deleteUserById(userId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${userId}`, { responseType: 'text' });
  }

  getAllInsertionsByUser(id: number | undefined, page: 1 | undefined): Observable<Page<BasicInsertion>> {
    return this.http.get<Page<BasicInsertion>>(`${this.apiUrl}/${id}?page=${page}`);
  }

}
