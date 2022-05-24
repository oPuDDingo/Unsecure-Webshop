import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Article} from "../models/article";
import {Order} from "../models/order";
import {Shoppingcart} from "../models/shoppingcart";
import {Address} from "../models/address";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient) {
  }

  getArticleById(articleNumber: number): Observable<Article> {
    return this.httpClient.get<Article>(this.url + 'articles/' + articleNumber);
  }

  getImageById(id: number): Observable<any> {
    return this.httpClient.get(this.url + 'images/' + id, {responseType: 'text'});
  }

  getOrder(id: number): Observable<Order> {
    return this.httpClient.get<Order>(this.url + 'orders/' + id);
  }

  getShoppingCart(): Observable<Shoppingcart> {
    return this.httpClient.get<Shoppingcart>(this.url + 'cart');
  }

  updateShoppingCart(shoppingCart: Shoppingcart): Observable<Shoppingcart> {
    let itemsPayload = {items: shoppingCart.itemList};
    return this.httpClient.put<any>(this.url + 'cart/items', itemsPayload);
  }

  createAddress(address: Address): Observable<Address> {
    let addressPayload = {
      "name": address.name,
      "country": address.country,
      "address": address.address,
      "address2": address.address2,
      "zipCode": address.zipCode,
      "city": address.city
    };
    return this.httpClient.post<Address>(this.url + 'user/addresses', addressPayload);
  }

  loadAddressById(id: number): Observable<Address> {
    return this.httpClient.get<Address>(this.url + 'user/addresses/' + id);
  }

  loadAllAddresses(): Observable<Address[]> {
    return this.httpClient.get<Address[]>(this.url + 'user/addresses');
  }

  updateAddress(address: Address): Observable<any> {
    let addressPayload = {
      ...address
    };
    return this.httpClient.put(this.url + 'user/addresses/' + address.id, addressPayload);
  }
}
