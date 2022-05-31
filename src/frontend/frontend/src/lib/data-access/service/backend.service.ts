import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import {Observable} from "rxjs";
import {Order, Article} from "../models";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  constructor( private httpClient: HttpClient ) { }

  getArticleById(articleNumber: number): Observable<Article> {
    return this.httpClient.get<Article>(this.url + 'articles/' + articleNumber);
  }

  getArticlesFrontpage(): Observable<Article[]> {
    return this.httpClient.get<Article[]>(this.url + 'articles?page=1&specifications=false');
  }

  loadOrders(): Observable<Order[]> {
    return this.httpClient.get<Order[]>(this.url + 'orders');
  }

  loadOrdersWithFullBody(orderNumber: number): Observable<Order> {
    return this.httpClient.get<Order>(this.url + 'orders/' + orderNumber);
  }

  getImageById(id: number): Observable<any> {
    return this.httpClient.get(this.url + 'pictures/' + id, {responseType: 'text'});
  }

}
