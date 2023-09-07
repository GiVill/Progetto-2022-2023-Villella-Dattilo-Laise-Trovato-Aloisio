import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { BuyingOfferDto } from '../model/buyingOfferDto';


@Injectable({
  providedIn: 'root'
})
export class BuyingOfferService {

  private apiUrl = 'https://localhost:8010/vintedProject-api/v1/insertions';

  constructor(private http: HttpClient) { }

  getAllOffers(): Observable<BuyingOfferDto[]> {
    return this.http.get<BuyingOfferDto[]>(`${this.apiUrl}`);
  }

  getOffersByUserId(userId: number): Observable<BuyingOfferDto[]> {
    return this.http.get<BuyingOfferDto[]>(`${this.apiUrl}/${userId}`);
  }

  addBuyingOffer(offer: BuyingOfferDto): Observable<BuyingOfferDto> {
    return this.http.post<BuyingOfferDto>(`${this.apiUrl}`, offer);
  }

  deleteOfferById(offerId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${offerId}`, { responseType: 'text' });
  }

}
