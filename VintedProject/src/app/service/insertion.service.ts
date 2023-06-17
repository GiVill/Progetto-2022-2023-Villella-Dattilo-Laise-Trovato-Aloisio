import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {BasicInsertion} from "../Model/basic-insertion.model";
import {Page} from "../Model/page.model";

@Injectable({
  providedIn: 'root'
})
export class InsertionService {
  private apiinsertionUrl = 'https://localhost:8010/vintedProject-api/v1/insertions';

  constructor(private http: HttpClient) { }

  getAllInsertions(page: number): Observable<Page<BasicInsertion>> {
    const params = new HttpParams().set('page', page.toString());
    return this.http.get<Page<BasicInsertion>>(`${this.apiinsertionUrl}`, { params });
  }


  getInsertionByBrand(brand: string | undefined, page: number): Observable<Page<BasicInsertion>> {
    const params = new HttpParams().set('page', page.toString());
    return this.http.get<Page<BasicInsertion>>(`${this.apiinsertionUrl}/${brand}`);
  }

  addInsertion(basicInsertion: BasicInsertion): Observable<BasicInsertion> {
    return this.http.post<BasicInsertion>(`${this.apiinsertionUrl}`, basicInsertion);
  }

  getInsertionById(id: number | undefined): Observable<BasicInsertion> {
    return this.http.get<BasicInsertion>(`${this.apiinsertionUrl}/${id}`);
  }

  deleteInsertionById(id: number): Observable<any> {
    return this.http.delete(`${this.apiinsertionUrl}/${id}`, { responseType: 'text' });
  }

  deleteAllInsertionsByUserId(userId: number): Observable<any> {
    const params = new HttpParams().set('userId', userId.toString());
    return this.http.delete(`${this.apiinsertionUrl}`, { params, responseType: 'text' });
  }


  findAllByTitleLike(pageNumber: number, search: string) {
    return this.http.get<Page<BasicInsertion>>(`${this.apiinsertionUrl}${search}?page=${pageNumber}`);
  }

  findAllByCategory(pageNumber: number, category: string) {
      return this.http.get<Page<BasicInsertion>>(`${this.apiinsertionUrl}${category}?page=${pageNumber}`);
  }



}
