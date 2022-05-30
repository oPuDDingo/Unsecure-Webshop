import {Article} from "../../models/article";
import {BackendService} from "../backend.service";
import {ReplaySubject} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class ArticleStore {
  articles: Article[] = [];

  constructor(private backendService: BackendService) {
  }

  loadArticleById(articleNumber: number): ReplaySubject<Article> {
    const articleSubject: ReplaySubject<Article> = new ReplaySubject<Article>(1);
    let index = this.articles.findIndex(article => article.articleNumber === articleNumber);
    if (index == -1) {
      this.backendService.getArticleById(articleNumber).subscribe(article => {
        this.articles.push(article);
        articleSubject.next(article);
      });
    } else {
      articleSubject.next(this.articles[index]);
    }
    return articleSubject;
  }
}
