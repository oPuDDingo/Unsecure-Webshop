import {Injectable} from "@angular/core";
import {BackendService} from "../backend.service";
import {ReplaySubject} from "rxjs";
import {Article} from "../../models";

@Injectable({
  providedIn: 'root'
})

export class ArticleStore {
  articles: Article[] = [];

  constructor(private backendService: BackendService) {
  }

  loadArticles(): ReplaySubject<Article> {
    const articleSubject: ReplaySubject<Article> = new ReplaySubject<Article>();
    this.backendService.getArtricles().subscribe(article => this.articles.push(article));
    return articleSubject;

  }

}
