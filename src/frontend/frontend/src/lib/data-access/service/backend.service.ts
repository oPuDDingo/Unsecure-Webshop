import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ShoppingCart} from "../models";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient) {
  }

  loadShoppingCart(): Observable<ShoppingCart> {
    return this.httpClient.get<ShoppingCart>(this.url + 'cart') // TODO brauch ich ein ID?
  }

  updateShoppingCart(shoppingCart: ShoppingCart): Observable<ShoppingCart> {
    let itemsPayload = {items: shoppingCart.itemList};
    return this.httpClient.put<any>(this.url + 'cart/items', itemsPayload)
  }

}
