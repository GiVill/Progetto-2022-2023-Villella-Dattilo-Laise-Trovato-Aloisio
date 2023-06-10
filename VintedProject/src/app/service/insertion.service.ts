import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {BasicInsertion} from "../Model/basic-insertion.model";
import {Page} from "../Model/page.model";

@Injectable({
  providedIn: 'root'
})
export class InsertionService {
  private apiUrl = 'http://localhost:8010/vintedProject-api/v1/insertions';

  constructor(private http: HttpClient) { }

  getAllInsertions(page: number): Observable<Page<BasicInsertion>> {
    const params = new HttpParams().set('page', page.toString());
    return this.http.get<Page<BasicInsertion>>(`${this.apiUrl}`, { params });
  }


  getInsertionByBrand(brand: string | undefined, page: number): Observable<Page<BasicInsertion>> {
    const params = new HttpParams().set('page', page.toString());
    return this.http.get<Page<BasicInsertion>>(`${this.apiUrl}/${brand}`);
  }

  addInsertion(basicInsertion: BasicInsertion): Observable<BasicInsertion> {
    return this.http.post<BasicInsertion>(`${this.apiUrl}`, basicInsertion);
  }

  getInsertionById(id: number): Observable<BasicInsertion> {
    return this.http.get<BasicInsertion>(`${this.apiUrl}/${id}`);
  }

  deleteInsertionById(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
  }

  deleteAllInsertionsByUserId(userId: number): Observable<any> {
    const params = new HttpParams().set('userId', userId.toString());
    return this.http.delete(`${this.apiUrl}`, { params, responseType: 'text' });
  }
}
