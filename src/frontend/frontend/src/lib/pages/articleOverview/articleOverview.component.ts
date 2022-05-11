import {Component, Input, OnInit} from "@angular/core";
import {Article} from "../../data-access/models/article";
import {ArticleStore} from "../../data-access/service/store/article.store";

@Component({
  selector: 'article-overview',
  templateUrl: './articleOverview.component.html',
  styleUrls: ['./articleOverview.component.scss']
})
export class ArticleOverviewComponent implements OnInit {
  // @ts-ignore
  @Input articleNumber: number;
  // @ts-ignore
  article: Article;

  constructor(private articleStore: ArticleStore) {

  }

  ngOnInit() {
    this.articleStore.loadArticleById(this.articleNumber).subscribe(article => this.article = article);
  }

}
