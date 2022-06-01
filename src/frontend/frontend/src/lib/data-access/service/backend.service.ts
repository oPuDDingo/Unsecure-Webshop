import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable, tap} from "rxjs";
import {Address, Article, Contact, Coupon, Order, SpecifiedItem, User} from "../models";
import {JsonObject} from "@angular/compiler-cli/ngcc/src/packages/entry_point";


@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  header: HttpHeaders = new HttpHeaders();

  constructor(private httpClient: HttpClient) {
    let key = sessionStorage.getItem('sessionKey');
    if (key != null) {
    }

  }

  getArtricles(): Observable<Article[]> {
    return this.httpClient.get<Article[]>(this.url + 'articles', {headers: this.header});
  }

  getArticlesByBrand(brand: string): Observable<Article[]> {
    return this.httpClient.get<Article[]>(this.url + 'articles?brand=' + brand + '&page=8', {headers: this.header});
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
    console.log(this.header)
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
    console.log(this.header)
    return this.httpClient.post(this.url + 'cart/items/', {...item}, {headers: this.header});
  }

  getCoupon(coupon: string): Observable<Coupon> {
    return this.httpClient.get<Coupon>(this.url + 'coupons/' + coupon, {headers: this.header});
  }

  loadWishList(): Observable<SpecifiedItem[]> {
    console.log(this.header);
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

  updateUser(userPayload: JsonObject): Observable<any> {
    return this.httpClient.put(this.url + 'user', userPayload, {observe: "response", headers: this.header})
      .pipe(
        tap(resp => {
          if (resp.status == 400) {
            throw new Error('Bad request!')
          } else {
            return resp.body;
          }
        })
      );
  }

  postNewsletter(): Observable<any> {
    return this.httpClient.post<any>(this.url + 'user/newsletter', {}, {headers: this.header});
  }

  loadUser(): Observable<User> {
    console.log(this.header);
    return this.httpClient.get<User>(this.url + 'user', {headers: this.header});
  }

  login(mail: string, password: string): Observable<any> {
    return this.httpClient.get<any>(this.url + 'user/login?mail=' + mail + '&password=' + password, {
      observe: "body"
    }).pipe(
      map(sessionKey => {
        sessionStorage.setItem('sessionKey', sessionKey);
        this.header.set('sessionID', sessionKey);
      })
    );
  }

  logout(): Observable<any> {
    let sessionKey = sessionStorage.getItem('sessionKey');
    return this.httpClient.post<any>(this.url + 'user/logout', {sessionKey}, {
      headers: this.header,
      observe: "response"
    }).pipe(
      map(response => {
        sessionStorage.removeItem('sessionKey');
        this.header.delete('sessionID');
      })
    );
  }

  register(firstname: string, lastname: string, mail: string, password: string): Observable<any> {
    return this.httpClient.post(this.url + 'user/register', {
      firstname,
      lastname,
      mail,
      password
    }, {observe: "body", responseType: "text"}).pipe(
      map(sessionKey => {
        sessionStorage.setItem('sessionKey', sessionKey)
        console.log("vor Registrierung: " + this.header.keys());
        this.header.append('sessionID', sessionKey);
        console.log("nach Registrierung: " + this.header.get("sessionID"));
        return sessionKey;
      })
    );
  }

}
