import {Component, OnInit} from "@angular/core";
import {ArticleStore} from "../../data-access/service/store/article.store";
import {Article, SpecifiedItem} from "../../data-access/models";
import {ActivatedRoute, Router} from "@angular/router";
import {ShoppingCartStore} from "../../data-access/service/store/shoppingCart.store";
import {WishlistStore} from "../../data-access/service/store/wishlist.store";

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

  constructor(private articleStore: ArticleStore, private route: ActivatedRoute, private routing: Router, private cartStore: ShoppingCartStore, private wishListStore: WishlistStore) {
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
    this.cartStore.addItem(this.specifiedItem).subscribe();
    this.routing.navigateByUrl('/cart');
  }

  onAddToWishList(): void {
    this.wishListStore.addItem(this.specifiedItem).subscribe();
    this.routing.navigateByUrl('/wishlist');
  }

  //Hier mal gucken wie man des verbessern kann
  openCommentField(){
    let row = document.createElement('div');
    row.className = 'row';
    row.innerHTML = `
      <br>
      <input type="text">`;
    // @ts-ignore
    document.querySelector('.inputField').appendChild(row);
  }

}
