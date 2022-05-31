import {Component} from "@angular/core";
import {SpecifiedItem} from "src/lib/data-access/models";
import {ShoppingCartStore} from "../../data-access/service/store/shoppingCart.store";

@Component({
  selector: 'shopping-cart',
  templateUrl: './shoppingCart.component.html',
  styleUrls: ['./shoppingCart.component.scss']
})
export class ShoppingCartComponent {
  // @ts-ignore
  itemList: SpecifiedItem[];

  amount: number = 0;
  quantity: number = 0;

  constructor(private shoppingCartStore: ShoppingCartStore) {
  }

  onItemDelete(itemId: number): void {
    this.shoppingCartStore.deleteItem(itemId);
    this.calculateAmount();
  }

  onItemChange(event: any): void {
    this.shoppingCartStore.updateItem(event.itemId, event.quantity).subscribe(itemList => {
      this.itemList = itemList;
      this.calculateAmount();
    });
  }

  ngOnInit(): void {
    this.shoppingCartStore.loadShoppingCart().subscribe(itemList => {
      this.itemList = itemList;
      this.calculateAmount();
    });
  }

  calculateAmount(): void {
    this.amount = 0;
    this.quantity = 0;
    if (this.itemList) {
      this.itemList.forEach(element => {
        this.amount += element.amount ? element.amount * element.quantity : 0;
        this.quantity += element.quantity;
      });
    }
  }
}
