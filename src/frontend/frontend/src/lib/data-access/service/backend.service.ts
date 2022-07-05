import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Address, Article, Contact, Coupon, Order, SpecifiedItem, User} from "../models";
import {RankingStudent} from "../models/rankingStudent";
import {JsonObject} from "@angular/compiler-cli/ngcc/src/utils";
import {CookieService} from "ngx-cookie-service";
import {Nletter} from "../models/nletter";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  header: HttpHeaders;
  ip: string = "";

  constructor(private httpClient: HttpClient, private cookieService: CookieService) {
    let key = this.cookieService.get('sessionKey').replace('sessionKey=', '');
    if (key != null) {
      this.header = new HttpHeaders({"sessionid": key});
    } else {
      this.header = new HttpHeaders({});
    }
  }

  getAlerts(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.url + 'flaws');
  }

  getArticles(): Observable<Article[]> {
    return this.httpClient.get<Article[]>(this.url + 'articles', {headers: this.header});
  }

  getArticleById(articleNumber: number): Observable<Article> {
    return this.httpClient.get<Article>(this.url + 'articles/' + articleNumber, {headers: this.header});
  }

  getArticlesFrontpage(): Observable<Article[]> {
    return this.httpClient.get<Article[]>(this.url + 'articles?page=1&specifications=false', {headers: this.header});
  }

  loadOrders(): Observable<Order[]> {
    return this.httpClient.get<Order[]>(this.url + 'orders', {headers: this.header});
  }

  loadOrdersWithFullBody(orderNumber: number): Observable<Order> {
    return this.httpClient.get<Order>(this.url + 'orders/' + orderNumber, {headers: this.header});
  }

  getImageById(id: number): Observable<any> {
    return this.httpClient.get(this.url + 'pictures/' + id, {responseType: 'text', headers: this.header});
  }

  postOrder(order: Order): Observable<string> {
    // @ts-ignore
    return this.httpClient.post<string>(this.url + 'orders/', {...order}, {
      observe: "response",
      headers: this.header
    }).pipe(
      map(resp => {
        return resp.headers.get("Location")
      })
    );
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
    return this.httpClient.post<Address>(this.url + 'user/addresses', addressPayload, {headers: this.header});
  }

  loadAddressById(id: number): Observable<Address> {
    return this.httpClient.get<Address>(this.url + 'user/addresses/' + id, {headers: this.header});
  }

  loadAllAddresses(): Observable<Address[]> {
    return this.httpClient.get<Address[]>(this.url + 'user/addresses', {headers: this.header});
  }

  updateAddress(address: Address): Observable<any> {
    let addressPayload = {
      ...address
    };
    return this.httpClient.put(this.url + 'user/addresses/' + address.id, addressPayload, {headers: this.header});
  }

  loadShoppingCart(): Observable<SpecifiedItem[]> {
    return this.httpClient.get<SpecifiedItem[]>(this.url + 'cart/items', {headers: this.header})
  }

  updateItemList(itemList: SpecifiedItem[]): Observable<SpecifiedItem[]> {
    let itemsPayload = {items: itemList};
    return this.httpClient.put<SpecifiedItem[]>(this.url + 'cart/items', itemsPayload, {headers: this.header})
  }

  updateShoppingCartItem(item: SpecifiedItem): Observable<SpecifiedItem> {
    let itemPayload = {...item};
    return this.httpClient.put<SpecifiedItem>(this.url + 'cart/items/' + item.id, itemPayload, {headers: this.header})
  }

  deleteItem(itemId: number): Observable<any> {
    return this.httpClient.delete<any>(this.url + 'cart/items/' + itemId, {headers: this.header});
  }

  addItemToShoppingCart(item: SpecifiedItem): Observable<any> {
    return this.httpClient.post(this.url + 'cart/items/', {...item}, {headers: this.header});
  }

  getCoupon(coupon: string): Observable<Coupon> {
    return this.httpClient.get<Coupon>(this.url + 'coupons/' + coupon, {headers: this.header});
  }

  loadWishList(): Observable<SpecifiedItem[]> {
    return this.httpClient.get<SpecifiedItem[]>(this.url + 'wishlist/items', {headers: this.header})
  }

  // updateWishList(specifiedItems: SpecifiedItem[]): Observable<SpecifiedItem[]> {
  //   let itemsPayload = {items: specifiedItems};
  //   return this.httpClient.put<SpecifiedItem[]>(this.url + 'wishlist/items', itemsPayload)
  // }

  addItemToWishList(item: SpecifiedItem): Observable<any> {
    return this.httpClient.post<any>(this.url + 'wishlist/items', {...item}, {headers: this.header});
  }

  updateWishListItem(item: SpecifiedItem): Observable<SpecifiedItem> {
    let itemPayload = {...item};
    return this.httpClient.put<SpecifiedItem>(this.url + 'wishlist/items/' + item.id, itemPayload, {headers: this.header})
  }

  deleteWishListItem(itemId: number): Observable<any[]> {
    return this.httpClient.delete<any>(this.url + 'wishlist/items/' + itemId, {headers: this.header});
  }

  deleteWishList(): Observable<any> {
    return this.httpClient.delete<any>(this.url + 'wishlist/items', {headers: this.header});
  }

  postContact(contact: Contact): Observable<Contact> {
    return this.httpClient.post<Contact>(this.url + 'contact', {...contact}, {headers: this.header});
  }

  updateUser(userPayload: JsonObject): Observable<User> {
    return this.httpClient.put<User>(this.url + 'user', userPayload, {headers: this.header});
  }

  getNewsletterStatus(): Observable<boolean> {
    return this.httpClient.get<string>(this.url + 'user/newsletter', {headers: this.header}).pipe(
      map(value => value == "true")
    );
  }

  postNewsletter(nletter: Nletter): Observable<any> {
    return this.httpClient.post<any>(this.url + 'user/newsletter', {...nletter}, {headers: this.header});
  }

  deleteNewsletter(): Observable<any> {
    return this.httpClient.delete(this.url + 'user/newsletter', {headers: this.header});
  }

  loadUser(): Observable<User> {
    return this.httpClient.get<User>(this.url + 'user', {headers: this.header});
  }

  loadUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(this.url + 'user/' + id);
  }

  searchArticles(searchInput: string): Observable<Article[]> {
    return this.httpClient.get<Article[]>(this.url + "articles?search=" + searchInput);
  }

  setLevel(level: number): Observable<any> {
    return this.httpClient.post<any>(this.url + 'admin/interface?level=' + level, {}, {headers: this.header});
  }

  getLevel(): Observable<number> {
    return this.httpClient.get<number>(this.url + 'admin/interface', {headers: this.header});
  }

  loadRankingStudents(): Observable<RankingStudent[]> {
    return this.httpClient.get<RankingStudent[]>(this.url + 'admin/interface', {headers: this.header});
  }

  shopReset(): Observable<any> {
    return this.httpClient.put(this.url + 'admin/interface', {}, {headers: this.header});
  }

  rankingReset(): Observable<any> {
    return this.httpClient.delete(this.url + 'admin/interface', {headers: this.header});
  }

}
