import {Component, OnInit} from '@angular/core';
import {Article} from "../../data-access/models";
import {ArticleStore} from "../../data-access/service/store/article.store";

@Component({
  selector: 'itemList',
  templateUrl: './itemList.component.html',
  styleUrls: ['./itemList.component.scss']
})
export class ItemListComponent implements OnInit {

  //@Input articleNumber: number;
  articles: Article[] = [];


  constructor(private articleStore: ArticleStore) {
  }

  ngOnInit() {
    this.articleStore.loadArticles().subscribe(articles => {
      this.articles = articles;
    });
  }

  selectBrand(brand: string) {
    this.articleStore.loadArticlesByBrand(brand).subscribe(articles => this.articles = articles);
  }

}
