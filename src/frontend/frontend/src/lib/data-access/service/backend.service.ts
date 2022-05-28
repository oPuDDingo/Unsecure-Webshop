import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient) {
  }

  postNewsletter(): Observable<any> {
    // @ts-ignore
    return this.httpClient.post<any>(this.url + 'user/newsletter');
  }
}
