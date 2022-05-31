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

  getImageById(id: number): Observable<any> {
    return this.httpClient.get(this.url + 'images/' + id, {responseType: 'text'});
  }

  getArticleById(articleNumber: number): Observable<Article> {
    return this.httpClient.get<Article>(this.url + 'articles/' + articleNumber);
  }

  getArticles(): Observable<Article[]> {
    return this.httpClient.get<Article[]>(this.url + 'articles');
  }
}
