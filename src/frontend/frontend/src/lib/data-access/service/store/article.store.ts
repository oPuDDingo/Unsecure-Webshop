import {Article} from "../../models/article";
import {BackendService} from "../backend.service";
import {Subject} from "rxjs";

export class ArticleStore {
  articles: Article[] = [];

  constructor(private backendService: BackendService) {
  }

  loadArticleById(articleNumber: number): Subject<Article> {
    const articleSubject: Subject<Article> = new Subject<Article>();
    let index = this.articles.findIndex(article => article.articleNumber === articleNumber);
    if (index == -1) {
      this.backendService.getArticleById(articleNumber).subscribe(article => {
        this.articles.push(article);
        articleSubject.next(article);
      })
    } else {
      articleSubject.next(this.articles[index]);
    }
    return articleSubject;
  }
}
