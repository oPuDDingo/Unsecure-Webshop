import {Component, Input, OnInit} from "@angular/core";
import {ArticleStore} from "../../data-access/service/store/article.store";
import {Article} from "../../data-access/models";


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
    console.log("test");
    this.articleStore.loadArticlesFrontpage().subscribe(articles => {
      this.articles = articles;
      console.log(articles);
    });
  }
}
