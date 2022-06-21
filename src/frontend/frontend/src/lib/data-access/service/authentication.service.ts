import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable, ReplaySubject} from "rxjs";
import {BackendService} from "./backend.service";
import {AddressStore} from "./store/address.store";
import {OrderStore} from "./store/order.store";
import {ShoppingCartStore} from "./store/shoppingCart.store";
import {UserStore} from "./store/user.store";
import {WishlistStore} from "./store/wishlist.store";

@Injectable({providedIn: "root"})
export class AuthenticationService {

  readonly url: string = 'http://localhost:4200/api/';
  statusSubject: ReplaySubject<boolean> = new ReplaySubject<boolean>(1);

  constructor(
    private httpClient: HttpClient,
    private backendService: BackendService,
    private addressStore: AddressStore,
    private orderStore: OrderStore,
    private shoppingCartStore: ShoppingCartStore,
    private userStore: UserStore,
    private wishlistStore: WishlistStore
  ) {

    if (sessionStorage.getItem("sessionKey") != null) {
      this.statusSubject.next(true);
    } else {
      this.statusSubject.next(false);
    }

  }

  login(mail: string, password: string): Observable<boolean> {
    return this.httpClient.get(this.url + 'user/login?mail=' + mail + '&password=' + password, {
      observe: "body", responseType: "text"
    }).pipe(
      map(sessionKey => {
        sessionStorage.setItem('sessionKey', sessionKey);
        this.backendService.header = new HttpHeaders({'sessionID': sessionKey});
        this.statusSubject.next(true);
        return true;
      })
    );
  }

  logout(): Observable<any> {
    let sessionKey = sessionStorage.getItem('sessionKey');
    return this.httpClient.post(this.url + 'user/logout', {sessionKey}, {
      headers: this.backendService.header,
      observe: "response"
    }).pipe(
      map(response => {
        sessionStorage.removeItem('sessionKey');
        this.backendService.header = new HttpHeaders({});
        this.statusSubject.next(false);
        this.cleanupStores();
      })
    );
  }

  register(firstname: string, lastname: string, mail: string, password: string): Observable<any> {
    return this.httpClient.post(this.url + 'user/register', {
      "firstName": firstname,
      "lastName": lastname,
      "mail": mail,
      "password": password
    }, {observe: "body", responseType: "text"}).pipe(
      map(sessionKey => {
        sessionStorage.setItem('sessionKey', sessionKey)
        this.backendService.header = new HttpHeaders({"sessionID": sessionKey});
        return sessionKey;
      })
    );
  }

  getStatus(): ReplaySubject<boolean> {
    return this.statusSubject;
  }

  cleanupStores(): void {
    this.addressStore.cleaningUp();
    this.orderStore.cleaningUp();
    this.shoppingCartStore.cleaningUp();
    this.userStore.cleaningUp();
    this.wishlistStore.cleaningUp();
  }
}
