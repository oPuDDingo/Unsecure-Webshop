import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient) {
  }
}
