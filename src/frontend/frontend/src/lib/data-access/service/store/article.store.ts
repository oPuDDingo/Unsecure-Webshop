import {Injectable} from "@angular/core";
import {BackendService} from "../backend.service";
import {ReplaySubject} from "rxjs";
import {Article} from "../../models/article";

@Injectable({
  providedIn: 'root'
})

export class ArticleStore {
  // @ts-ignore
  articles: Article[];
  articleSubject: ReplaySubject<Article[]> = new ReplaySubject<Article[]>(1);

  constructor(private backendService: BackendService) {
  }

  loadArticles(): ReplaySubject<Article[]> {

    if (!this.articles) {
      this.backendService.getArtricles().subscribe(articles => {
        this.articles = articles;
        this.articleSubject.next(articles);
      });

    } else {
      this.articleSubject.next(this.articles);
    }

    return this.articleSubject;

  }

  loadArticleById(id: number): ReplaySubject<Article> {
    const articleSubject: ReplaySubject<Article> = new ReplaySubject<Article>(1);
    let index: number = this.articles.findIndex(a => a.articleNumber === id);
    if (index === -1) {
      this.backendService.getArticleById(id).subscribe(article => {
        this.articles.push(article);
        articleSubject.next(article);
      })
    } else {
      articleSubject.next(this.articles[index]);
    }
    return articleSubject;
  }

  loadArticlesByBrand(brand: string): ReplaySubject<Article[]> {
    if (!this.articles) {
      this.backendService.getArticlesByBrand(brand).subscribe(articles => {
        this.articles = articles;
        this.articleSubject.next(articles);
      });

    } else {
      this.articleSubject.next(this.articles);
    }

    return this.articleSubject;

  }


}
