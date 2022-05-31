import {Component, EventEmitter, Input, Output} from "@angular/core";
import {Article} from "../../../data-access/models/article";

@Component({
  selector: 'article-overview-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent {
  // @ts-ignore
  @Input article: Article;

  @Output() changeQuantityEvent: EventEmitter<number> = new EventEmitter<number>();
  @Output() addToShoppingCartEvent: EventEmitter<any> = new EventEmitter<any>();
  @Output() addToWishListEvent: EventEmitter<any> = new EventEmitter<any>();

  onChangeQuantity(value: number): void {
    this.changeQuantityEvent.emit(value);
  }

  onAddToWishList(): void {
    this.addToWishListEvent.emit();
  }

  onAddToShoppingCart(): void {
    this.addToShoppingCartEvent.emit();
  }
}

