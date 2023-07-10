import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserDto} from "../Model/userDto";
import {BasicInsertionDto} from "../Model/basicInsertionDto";
import {Configuration} from "../configuration";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'https://localhost:8010/vintedProject-api/v1/users';
  public defaultHeaders = new HttpHeaders();
  public configuration = new Configuration();



  constructor(private http: HttpClient, protected httpClient: HttpClient) { }

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

  public updateUserPassword(body: string, idUser: number, observe?: 'body', reportProgress?: boolean): Observable<boolean>;
  public updateUserPassword(body: string, idUser: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<boolean>>;
  public updateUserPassword(body: string, idUser: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<boolean>>;
  public updateUserPassword(body: string, idUser: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

    if (body === null || body === undefined) {
      throw new Error('Required parameter body was null or undefined when calling updateUserPassword.');
    }

    if (idUser === null || idUser === undefined) {
      throw new Error('Required parameter idUser was null or undefined when calling updateUserPassword.');
    }

    let headers = this.defaultHeaders;

    // authentication (bearerAuth) required
    if (this.configuration.accessToken) {
      const accessToken = typeof this.configuration.accessToken === 'function'
        ? this.configuration.accessToken()
        : this.configuration.accessToken;
      headers = headers.set('Authorization', 'Bearer ' + accessToken);
    }
    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      '*/*'
    ];
    const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
    if (httpHeaderAcceptSelected != undefined) {
      headers = headers.set('Accept', httpHeaderAcceptSelected);
    }

    // to determine the Content-Type header
    const consumes: string[] = [
      'application/json'
    ];
    const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
    if (httpContentTypeSelected != undefined) {
      headers = headers.set('Content-Type', httpContentTypeSelected);
    }

    return this.httpClient.request<boolean>('put',`${this.apiUrl}/v1/update-users-password/${encodeURIComponent(String(idUser))}`,
      {
        body: body,
        withCredentials: this.configuration.withCredentials,
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }

  public updateUserNickname(body: string, idUser: number, observe?: 'body', reportProgress?: boolean): Observable<boolean>;
  public updateUserNickname(body: string, idUser: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<boolean>>;
  public updateUserNickname(body: string, idUser: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<boolean>>;
  public updateUserNickname(body: string, idUser: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

    if (body === null || body === undefined) {
      throw new Error('Required parameter body was null or undefined when calling updateUserNickname.');
    }

    if (idUser === null || idUser === undefined) {
      throw new Error('Required parameter idUser was null or undefined when calling updateUserNickname.');
    }

    let headers = this.defaultHeaders;

    // authentication (bearerAuth) required
    if (this.configuration.accessToken) {
      const accessToken = typeof this.configuration.accessToken === 'function'
        ? this.configuration.accessToken()
        : this.configuration.accessToken;
      headers = headers.set('Authorization', 'Bearer ' + accessToken);
    }
    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      '*/*'
    ];
    const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
    if (httpHeaderAcceptSelected != undefined) {
      headers = headers.set('Accept', httpHeaderAcceptSelected);
    }

    // to determine the Content-Type header
    const consumes: string[] = [
      'application/json'
    ];
    const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
    if (httpContentTypeSelected != undefined) {
      headers = headers.set('Content-Type', httpContentTypeSelected);
    }

    return this.httpClient.request<boolean>('put',`${this.apiUrl}/v1/update-users-nickname/${encodeURIComponent(String(idUser))}`,
      {
        body: body,
        withCredentials: this.configuration.withCredentials,
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }




}
