import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Payment} from "../Model/payment.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private apiUrl = 'http://localhost:8010/vintedProject-api/v1/insertions';

  constructor(private http: HttpClient) { }

  addPayment(payment: Payment): Observable<Payment> {
    return this.http.post<Payment>(`${this.apiUrl}`, payment);
  }

  deletePaymentById(paymentId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${paymentId}`, { responseType: 'text' });
  }

  getAllPayments(): Observable<Payment[]> {
    return this.http.get<Payment[]>(`${this.apiUrl}`);
  }

  getPaymentById(paymentId: number): Observable<Payment> {
    return this.http.get<Payment>(`${this.apiUrl}/${paymentId}`);
  }


}
