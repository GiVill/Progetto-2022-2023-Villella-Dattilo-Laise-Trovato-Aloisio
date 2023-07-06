import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserDto} from "../Model/userDto";
import {BasicInsertionDto} from "../Model/basicInsertionDto";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'https://localhost:8010/vintedProject-api/v1/users';

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<UserDto[]> {
    return this.http.get<UserDto[]>(`${this.apiUrl}`);
  }

  getUserById(userId: number | undefined): Observable<UserDto> {
    return this.http.get<UserDto>(`${this.apiUrl}/${userId}`);
  }

  deleteUserById(userId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${userId}`, { responseType: 'text' });
  }

  getAllInsertionsByUser(id: number | undefined, page: number): Observable<BasicInsertionDto> {
    return this.http.get<BasicInsertionDto>(`${this.apiUrl}/insertions/${id}?page=${page}`);
  }

  createUser(userData: any) {
    return this.http.post(`'https://localhost:8010/vintedProject-api/v1/singin`, userData);
  }


}
