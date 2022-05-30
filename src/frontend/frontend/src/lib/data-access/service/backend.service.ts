import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient) {
  }

  postNewsletter(): Observable<any> {
    return this.httpClient.post<any>(this.url + 'user/newsletter', {});
  }

  loadUser(): Observable<User> {
    return this.httpClient.get<User>(this.url + 'user/information');
  }
}
