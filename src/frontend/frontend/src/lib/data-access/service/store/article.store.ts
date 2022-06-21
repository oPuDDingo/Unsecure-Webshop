import {Injectable} from "@angular/core";
import {BackendService} from "../backend.service";
import {ReplaySubject} from "rxjs";
import {Article} from "../../models";

@Injectable({
  providedIn: 'root'
})

export class ArticleStore {
  // @ts-ignore
  articles: Article[] = [];
  // @ts-ignore
  smallArticles: Article[];
  articleSubject: ReplaySubject<Article[]> = new ReplaySubject<Article[]>(1);
  articleSubjectFrontpage: ReplaySubject<Article[]> = new ReplaySubject<Article[]>(1);

  constructor(private backendService: BackendService) {
  }

  loadArticles(): ReplaySubject<Article[]> {
    if (this.articles.length == 0) {
      this.backendService.getArticles().subscribe(articles => {
        this.articles = articles;
        this.articleSubject.next(articles);
      });

    } else {
      this.articleSubject.next(this.articles);
    }

    return this.articleSubject;

  }

  loadArticlesFrontpage(): ReplaySubject<Article[]> {
    if (!this.smallArticles) {
      this.backendService.getArticlesFrontpage().subscribe(articles => {
        this.smallArticles = articles;
        this.articleSubjectFrontpage.next(articles);
      })
    } else {
      this.articleSubjectFrontpage.next(this.smallArticles);
    }
    return this.articleSubjectFrontpage;
  }

  loadArticleById(id: number): ReplaySubject<Article> {
    const articleSubject: ReplaySubject<Article> = new ReplaySubject<Article>(1);
    let index: number = this.articles.findIndex(a => a.articleNumber === id);
    if (index === -1) {
      this.backendService.getArticleById(id).subscribe(article => {
        articleSubject.next(article);
      })
    } else {
      articleSubject.next(this.articles[index]);
    }
    return articleSubject;
  }

  loadArticlesByBrand(brand: string): ReplaySubject<Article[]> {
      this.backendService.getArticlesByBrand(brand).subscribe(articles => {
        this.articles = articles;
        this.articleSubject.next(articles);
      });

    return this.articleSubject;
  }

}
