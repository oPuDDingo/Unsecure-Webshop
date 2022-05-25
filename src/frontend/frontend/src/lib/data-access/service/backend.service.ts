import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SpecifiedItem} from "../models";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient) {
  }

  loadWishList(): Observable<SpecifiedItem[]> {
    return this.httpClient.get<SpecifiedItem[]>(this.url + 'wishlist/items')
  }

  updateWishList(specifiedItems: SpecifiedItem[]): Observable<SpecifiedItem[]> {
    let itemsPayload = {items: specifiedItems};
    return this.httpClient.put<SpecifiedItem[]>(this.url + 'wishlist/items', itemsPayload)
  }

  updateWishListItem(item: SpecifiedItem): Observable<SpecifiedItem> {
    let itemPayload = {...item};
    return this.httpClient.put<SpecifiedItem>(this.url + 'wishlist/items/' + item.id, itemPayload)
  }

  deleteWishListItem(itemId: number): Observable<any[]> {
    return this.httpClient.delete<any>(this.url + 'wishlist/items/' + itemId);
  }

  deleteWishList(): Observable<any> {
    return this.httpClient.delete<any>(this.url + 'wishlist/items');
  }
}
