import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Wishlist} from "../models/wishlist";
import {Observable} from "rxjs";
import {SpecifiedItem} from "../models";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient) {
  }

  loadWishList(): Observable<Wishlist> {
    return this.httpClient.get<Wishlist>(this.url + 'wishlist') // TODO brauch ich eine ID?
  }

  updateWishList(wishlist: Wishlist): Observable<Wishlist> {
    let itemsPayload = {items: wishlist.itemList};
    return this.httpClient.put<Wishlist>(this.url + 'wishlist/items', itemsPayload)
  }

  updateWishListItem(item: SpecifiedItem): Observable<SpecifiedItem> {
    let itemPayload = {...item};
    return this.httpClient.put<SpecifiedItem>(this.url + 'wishlist/items/' + item.id, itemPayload)
  }

  deleteWishListItem(itemId: number): Observable<Wishlist> {
    return this.httpClient.delete<any>(this.url + 'wishlist/items/' + itemId);
  }

  deleteWishList(): Observable<Wishlist> {
    return this.httpClient.delete<any>(this.url + 'wishlist/items');
  }
}
