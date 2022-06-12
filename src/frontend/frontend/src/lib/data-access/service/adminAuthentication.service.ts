import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {BackendService} from "./backend.service";
import {AddressStore} from "./store/address.store";
import {OrderStore} from "./store/order.store";
import {ShoppingCartStore} from "./store/shoppingCart.store";
import {UserStore} from "./store/user.store";
import {WishlistStore} from "./store/wishlist.store";

@Injectable({providedIn: "root"})
export class AdminAuthenticationService {

  userType: string = "User";

  readonly url: string = 'http://localhost:4200/api/';

  constructor(
    private httpClient: HttpClient,
    private backendService: BackendService
  ) {
  }

  login(username: string, password: string): Observable<any> {
    return this.httpClient.get(this.url + 'admin/login?username=' + username + '&password=' + password, {
      observe: "body", responseType: "text"
    }).pipe(
      map(sessionKey => {
        sessionStorage.setItem('sessionKey', sessionKey);
        this.backendService.header = new HttpHeaders({'sessionID': sessionKey});
      })
    );
  }

  logout(): Observable<any> {
    let sessionKey = sessionStorage.getItem('sessionKey');
    return this.httpClient.post(this.url + 'admin/logout', {sessionKey}, {
      headers: this.backendService.header,
      observe: "response"
    }).pipe(
      map(response => {
        sessionStorage.removeItem('sessionKey');
        this.backendService.header = new HttpHeaders({});
      })
    );
  }
}
