import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Order} from "../models";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';


  constructor(private httpClient: HttpClient) {
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
