import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {BackendService} from "./backend.service";

@Injectable({providedIn: "root"})
export class AuthenticationService {

  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient, private backendService: BackendService) {
  }

  login(mail: string, password: string): Observable<any> {
    return this.httpClient.get(this.url + 'user/login?mail=' + mail + '&password=' + password, {
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
    return this.httpClient.post(this.url + 'user/logout', {sessionKey}, {
      headers: this.backendService.header,
      observe: "response"
    }).pipe(
      map(response => {
        sessionStorage.removeItem('sessionKey');
        this.backendService.header = new HttpHeaders({});
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
}
