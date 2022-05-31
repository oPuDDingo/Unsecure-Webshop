import {Component, Input, OnInit} from "@angular/core";
import {Article} from "../../data-access/models/article";
import {ArticleStore} from "../../data-access/service/article.store";

@Component({
  selector: 'board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})

export class BoardComponent implements OnInit {

  // @ts-ignore
  @Input() articles: Article[];

  constructor(private articleStore: ArticleStore) {
  }

  ngOnInit() {
    this.articleStore.loadArticles().subscribe(articles => this.articles = articles);
  }
}
