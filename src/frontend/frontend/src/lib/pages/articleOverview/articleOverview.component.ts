import {Component, OnInit} from "@angular/core";
import {ArticleStore} from "../../data-access/service/store/article.store";
import {SpecifiedItem} from "../../data-access/models/specifiedItem";
import {ActivatedRoute} from "@angular/router";
import {Article} from "../../data-access/models/article";

@Component({
  selector: 'article-overview',
  templateUrl: './articleOverview.component.html',
  styleUrls: ['./articleOverview.component.scss']
})
export class ArticleOverviewComponent implements OnInit {
  // @ts-ignore
  article: Article = undefined;

  // @ts-ignore
  specifiedItem: SpecifiedItem;

  constructor(private articleStore: ArticleStore, private route: ActivatedRoute) {
  }

  ngOnInit() {
    let articleNumber: number = this.route.snapshot.params['id'];
    this.articleStore.loadArticleById(articleNumber).subscribe(article => {
      this.article = article;
      this.specifiedItem = {articleNumber: this.article.articleNumber, quantity: 1, gbSize: 128, color: 'red'};
    });
  }

  onUpdateQuantity(value: number): void {
    this.specifiedItem.quantity = value;
  }

  onUpdateGbSize(value: number): void {
    this.specifiedItem.gbSize = value;
  }

  onUpdateColor(value: string): void {
    this.specifiedItem.color = value;
  }

  onAddToShoppingCart(): void {
    //toDo: add to cart Service
    //toDo: redirect to /shoppingCart
  }

  onAddToWishList(): void {
    //toDo: add to wish list Service
    //toDo: redirect to /wishList
  }

}
