import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderDto} from "../Model/orderDto";
import { PageOrderDto} from "../Model/pageOrderDto";
import {BasicInsertionDto} from "../Model/basicInsertionDto";



@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = 'https://localhost:8010/vintedProject-api/v1/orders';

  constructor(private http: HttpClient) { }

  addOrder(order: { creationDate: string; userId: number; insertionId: number | undefined }): Observable<OrderDto> {
    console.log(order);
    return this.http.post<OrderDto>(`${this.apiUrl}`, order);
  }

  getOrderByUserId(id: number, page: number): Observable<PageOrderDto> {
    const params = new HttpParams().set('page', page.toString());
    return this.http.get<PageOrderDto>(`${this.apiUrl}/${id}`, { params });
  }

  getAllOrders(page: number): Observable<PageOrderDto> {
    const params = new HttpParams().set('page', page.toString());
    return this.http.get<PageOrderDto>(`${this.apiUrl}`, { params });
  }

  deleteOrderById(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
  }
}
