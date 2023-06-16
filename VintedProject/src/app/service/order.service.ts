import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import Order = jasmine.Order;
import {Page} from "../Model/page.model";



@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = 'https://localhost:8010/vintedProject-api/v1/insertions';

  constructor(private http: HttpClient) { }

  addOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(`${this.apiUrl}`, order);
  }

  getOrderById(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.apiUrl}/${id}`);
  }

  getAllOrders(page: number): Observable<Page<Order>> {
    const params = new HttpParams().set('page', page.toString());
    return this.http.get<Page<Order>>(`${this.apiUrl}`, { params });
  }

  deleteOrderById(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
  }
}
