import {Component, Input, OnInit} from "@angular/core";
import {Article} from "../../data-access/models/article";
import {ArticleStore} from "../../data-access/service/store/article.store";
import {SpecifiedItem} from "../../data-access/models/specifiedItem";

@Component({
  selector: 'article-overview',
  templateUrl: './articleOverview.component.html',
  styleUrls: ['./articleOverview.component.scss']
})
export class ArticleOverviewComponent implements OnInit {
  // @ts-ignore
  @Input articleNumber: number;
  // @ts-ignore
  article: Article = {
    articleNumber: 1,
    modelName: "Iphone 11",
    amount: 1099.99,
    stars: 4,
    numberOfValuation: 1023123,
    operatingSystem: "IOS",
    releaseDate: "05.09.2019",
    availability: true,
    picture: ["iphone11"],
    brand: "Apple",
    comments: ["Super Ding", "Find ich klasse"],
    resolution: "1792 x 828",
    screen: "IPS"
  };

  specifiedItem: SpecifiedItem = { articleNumber: this.article.articleNumber, quantity: 1, gbSize: 128, color: 'red'};

  constructor(private articleStore: ArticleStore) {
  }

  ngOnInit() {

    // this.articleStore.loadArticleById(this.articleNumber).subscribe(article => this.article = article);
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
