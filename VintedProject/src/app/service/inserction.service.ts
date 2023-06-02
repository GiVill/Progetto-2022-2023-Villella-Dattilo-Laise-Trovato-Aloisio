import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BasicInsertion} from "../Model/basic-insertion.model";

@Injectable({
  providedIn: 'root'
})
export class InserctionService {

  constructor(private httpClient: HttpClient) { }



  public getAllProducts(pageNumber: number, searchKeyword: string = ""): Observable<BasicInsertion[]> {
    const url = `http://localhost:8080/getAllProducts?pageNumber=${pageNumber}`;
    return this.httpClient.get<BasicInsertion[]>(url);
  }



  public getProductById(InsertionId){
    console.log(InsertionId)
    return this.httpClient.get<BasicInsertion>("http://localhost:8080/***/"+InsertionId);
  }
  public getProductByCategory(InsertionCategory){
    console.log(InsertionCategory)
    return this.httpClient.get<BasicInsertion[]>("http://localhost:8080/***/"+InsertionCategory);
  }

  public getProductsBySearch(Search){
    return this.httpClient.get<BasicInsertion[]>("http://localhost:8080/***/"+Search);
  }


  /*
    public deleteProduct(InsertionId){
      return this.httpClient.delete("http://localhost:8080/---/"+InsertionId);
    }

  */

}

