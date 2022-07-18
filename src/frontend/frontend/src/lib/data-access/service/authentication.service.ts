import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable, ReplaySubject} from "rxjs";
import {BackendService} from "./backend.service";
import {AddressStore} from "./store/address.store";
import {OrderStore} from "./store/order.store";
import {ShoppingCartStore} from "./store/shoppingCart.store";
import {UserStore} from "./store/user.store";
import {WishlistStore} from "./store/wishlist.store";
import {UserTypes} from "../enums/userTypes";
import {CookieService} from "ngx-cookie-service";
import {Statics} from "./statics";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  statusSubject: ReplaySubject<boolean> = new ReplaySubject<boolean>(1);
  statusAdminSubject: ReplaySubject<boolean> = new ReplaySubject<boolean>(1);
  userType: UserTypes = UserTypes.User;
  header: HttpHeaders = new HttpHeaders();

  constructor(
    public backendService: BackendService,
    private httpClient: HttpClient,
    private addressStore: AddressStore,
    private orderStore: OrderStore,
    private shoppingCartStore: ShoppingCartStore,
    private userStore: UserStore,
    private wishlistStore: WishlistStore,
    private cookieService: CookieService
  ) {
    if (this.cookieService.check("sessionKey")) {
      this.statusSubject.next(true);
      this.statusAdminSubject.next(true);
    }else {
      this.statusSubject.next(false);
      this.statusAdminSubject.next(false);
    }
  }

  login(mail: string, password: string): Observable<boolean> {
    return this.httpClient.get(Statics.url + 'users/login?mail=' + mail + '&password=' + password, {
      observe: "body", responseType: "text", headers: this.backendService.getHeader()
    }).pipe(
      map(sessionKey => {
        this.cookieService.set('sessionKey', sessionKey);
        this.backendService.sessionKey = sessionKey;
        this.statusSubject.next(true);
        this.userType = UserTypes.User;
        return true;
      })
    );
  }

  logout(): Observable<any> {
    if (this.cookieService != undefined) {
      let sessionKey = this.cookieService.get('sessionKey').replace('sessionKey=', '');
      this.cookieService.delete('sessionKey');
      this.backendService.sessionKey = "";
      this.statusSubject.next(false);
      this.userType = UserTypes.User;
      this.cleanupStores();
      return this.httpClient.post(Statics.url + 'users/logout', {sessionKey}, {
        headers: this.backendService.getHeader(),
        observe: "response",
      });
    }
    return new Observable<any>();
  }

  adminLogin(username: string, password: string): Observable<any> {
    return this.httpClient.get(Statics.url + 'admin/login?username=' + username + '&password=' + password, {
      observe: "body", responseType: "text", headers: this.backendService.getHeader()
    }).pipe(
      map(sessionKey => {
        this.cookieService.set("sessionKey", sessionKey);
        this.backendService.sessionKey = sessionKey;
        this.statusSubject.next(true);
        this.userType = UserTypes.Admin;
        this.statusAdminSubject.next(true);
      })
    );
  }

  adminLogout(): Observable<any> {
    if (this.cookieService != undefined) {
      let sessionKey = this.cookieService.get('sessionKey').replace('sessionKey=', '');
      this.cookieService.delete('sessionKey');
      this.statusSubject.next(false);
      this.statusAdminSubject.next(false);
      this.backendService.sessionKey = "";
      this.userType = UserTypes.User;
      this.cleanupStores();

      return this.httpClient.post(Statics.url + 'admin/logout', {sessionKey}, {
        headers: this.backendService.getHeader(),
        observe: "response"
      });
    }
    return new Observable<any>();

  }

  register(firstname: string, lastname: string, mail: string, password: string): Observable<any> {
    return this.httpClient.post(Statics.url + 'users/register', {
      "firstName": firstname,
      "lastName": lastname,
      "mail": mail,
      "password": password
    }, {observe: "body", responseType: "text", headers: this.backendService.getHeader()}).pipe(
      map(sessionKey => {
        this.cookieService.set("sessionKey", sessionKey);
        this.statusSubject.next(true);
        this.backendService.sessionKey = sessionKey;
        return sessionKey;
      })
    );
  }

  getStatus(): ReplaySubject<boolean> {
    return this.statusSubject;
  }

  getAdminStatus(): ReplaySubject<boolean>{
    return this.statusAdminSubject;
  }

  cleanupStores(): void {
    this.addressStore.cleaningUp();
    this.orderStore.cleaningUp();
    this.shoppingCartStore.cleaningUp();
    this.userStore.cleaningUp();
    this.wishlistStore.cleaningUp();
    this.userType = UserTypes.User;
  }
}
