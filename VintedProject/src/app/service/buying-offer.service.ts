import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BuyingOffer} from "../Model/buying-offer.model";

@Injectable({
  providedIn: 'root'
})
export class BuyingOfferService {

  private apiUrl = 'http://localhost:8010/vintedProject-api/v1/insertions';

  constructor(private http: HttpClient) { }

  getAllOffers(): Observable<BuyingOffer[]> {
    return this.http.get<BuyingOffer[]>(`${this.apiUrl}`);
  }

  getOffersByUserId(userId: number): Observable<BuyingOffer[]> {
    return this.http.get<BuyingOffer[]>(`${this.apiUrl}/${userId}`);
  }

  addBuyingOffer(offer: BuyingOffer): Observable<BuyingOffer> {
    return this.http.post<BuyingOffer>(`${this.apiUrl}`, offer);
  }

  deleteOfferById(offerId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${offerId}`, { responseType: 'text' });
  }

}
