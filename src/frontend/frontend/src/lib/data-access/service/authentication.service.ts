import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";

@Injectable({providedIn: "root"})
export class AuthenticationService {

  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient) {

  }

  login(mail: string, password: string): Observable<any> {
    return this.httpClient.post<any>(this.url + 'user/login', {
      mail,
      password
    }).pipe(
      map(sessionKey => {
        sessionStorage.setItem('sessionKey', sessionKey);
      })
    );
  }

  logout(): Observable<any> {
    let sessionKey = sessionStorage.getItem('sessionKey');
    return this.httpClient.post<any>(this.url + 'user/logout', {sessionKey}).pipe(
      map(any => {
        sessionStorage.removeItem('sessionKey');
      })
    );
  }

  register(firstname: string, lastname: string, mail: string, password: string): Observable<any> {
    return this.httpClient.post<any>(this.url + 'user/register', {
      firstname,
      lastname,
      mail,
      password
    }).pipe(
      map(sessionKey => {
        sessionStorage.setItem('sessionKey', sessionKey);
      })
    );
  }
}
