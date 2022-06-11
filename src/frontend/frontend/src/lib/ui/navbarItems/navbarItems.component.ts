import {Component} from "@angular/core";
import {ArticleStore} from "../../data-access/service/store/article.store";
import {Article} from "../../data-access/models";

@Component({
  selector: 'navbar-items',
  templateUrl: './navbarItems.component.html',
  styleUrls: ['./navbarItems.component.scss']
})

export class NavbarItemsComponent {

  articles: Article[] = [];


  constructor(private articleStore: ArticleStore) {
  }

  selectBrand(brand: string) {
    this.articleStore.loadArticlesByBrand(brand).subscribe(articles => this.articles = articles);
  }
}
