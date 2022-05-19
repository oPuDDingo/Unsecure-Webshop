import {Injectable} from "@angular/core";
import {Article} from "../../models/article";
import {BackendService} from "../backend.service";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class ArticleStore {
  articles: Article[] = [];

  constructor(private backendService: BackendService) {
  }

  loadArticles(): Subject<Article> {
    const articleSubject: Subject<Article> = new Subject<Article>();
    return articleSubject;

  }

}
