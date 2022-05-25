import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ShoppingCart, SpecifiedItem} from "../models";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient) {
  }

  loadShoppingCart(): Observable<SpecifiedItem[]> {
    return this.httpClient.get<SpecifiedItem[]>(this.url + 'cart/items')
  }

  updateItemList(itemList: SpecifiedItem[]): Observable<ShoppingCart> {
    let itemsPayload = {items: itemList};
    return this.httpClient.put<ShoppingCart>(this.url + 'cart/items', itemsPayload)
  }

  updateShoppingCartItem(item: SpecifiedItem): Observable<SpecifiedItem> {
    let itemPayload = {...item};
    return this.httpClient.put<SpecifiedItem>(this.url + 'cart/items/' + item.id, itemPayload)
  }

  deleteItem(itemId: number): Observable<any> {
    return this.httpClient.delete<any>(this.url + 'cart/items/' + itemId);
  }

}
