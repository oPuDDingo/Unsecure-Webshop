import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Article} from "../models/article";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient) {
  }

  getArtricles(): Observable<Article> {
    return this.httpClient.get<Article>(this.url + 'articles?page=8');
  }

  getArticlesbyBrand(brand: string): Observable<Article> {
    return this.httpClient.get<Article>(this.url + 'articles?brand=' + brand + '&page=8');
  }

  getArticlesbyPrice(amount: number): Observable<Article> {
    return this.httpClient.get<Article>(this.url + 'articles?price=' + amount + '&page=8');
  }

  getPictureByName(name: string): Observable<Blob> {
    return this.httpClient.get(this.url + 'images/' + name, {responseType: 'blob'});
  }
}
