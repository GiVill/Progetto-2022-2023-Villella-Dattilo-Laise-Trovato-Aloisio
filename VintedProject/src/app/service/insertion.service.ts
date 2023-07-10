import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {PageBasicInsertionDto} from "../Model/pageBasicInsertionDto";
import {BasicInsertionDto} from "../Model/basicInsertionDto";
import { ProvaDto } from '../Model/provadto';


@Injectable({
  providedIn: 'root'
})
export class InsertionService {
  private apiinsertionUrl = 'https://localhost:8010/vintedProject-api/v1/insertions';

  constructor(private http: HttpClient) { }

  getAllInsertions(page: number): Observable<PageBasicInsertionDto> {
    const params = new HttpParams().set('page', page.toString());
    return this.http.get<PageBasicInsertionDto>(`${this.apiinsertionUrl}`, { params });
  }


  getInsertionByBrand(brand: string | undefined, page: number): Observable<PageBasicInsertionDto> {
    const params = new HttpParams().set('page', page.toString());
    return this.http.get<PageBasicInsertionDto>(`${this.apiinsertionUrl}/${brand}`);
  }

  addInsertion(insertion: BasicInsertionDto, image : File){
    console.log(insertion);
    const provaDto : ProvaDto ={
      insertionDto : insertion,
      img : image
    }
    return this.http.post<BasicInsertionDto>(`${this.apiinsertionUrl}`,provaDto);
  }

  getInsertionById(id: number | undefined): Observable<BasicInsertionDto> {
    return this.http.get<BasicInsertionDto>(`${this.apiinsertionUrl}/${id}`);
  }

  deleteInsertionById(id: number): Observable<any> {
    return this.http.delete(`${this.apiinsertionUrl}/${id}`, { responseType: 'text' });
  }

  deleteAllInsertionsByUserId(userId: number): Observable<any> {
    const params = new HttpParams().set('userId', userId.toString());
    return this.http.delete(`${this.apiinsertionUrl}`, { params, responseType: 'text' });
  }


  findAllByTitleLike(pageNumber: number, search: string) {
    return this.http.get<PageBasicInsertionDto>(`${this.apiinsertionUrl}/title/${search}/${pageNumber}`);
  }

  findAllByCategory(pageNumber: number, category: string) {
      return this.http.get<PageBasicInsertionDto>(`${this.apiinsertionUrl}${category}?page=${pageNumber}`);
  }



}
