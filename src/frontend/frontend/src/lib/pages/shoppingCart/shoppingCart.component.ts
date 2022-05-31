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
    this.itemList = [{
      id: 1,
      articleNumber: 23424342,
      name: "Iphone 11",
      quantity: 1,
      gbSize: 128,
      color: "blue",
      amount: 1299.99,
      pictureId: 1
    },
      {
        id: 2,
        articleNumber: 41561651,
        name: "Iphone 10",
        quantity: 2,
        gbSize: 128,
        color: "white",
        amount: 899.99,
        pictureId: 2
      },
      {
        id: 3,
        articleNumber: 724345,
        name: "Iphone 13",
        gbSize: 256,
        quantity: 1,
        amount: 1499.99,
        color: "black",
        pictureId: 3
      },
      {
        id: 4,
        articleNumber: 9873198,
        name: "Samsung S22",
        gbSize: 128,
        quantity: 4,
        amount: 800.00,
        color: "red",
        pictureId: 4
      },
      {
        id: 5,
        name: "Iphone 7",
        gbSize: 512,
        articleNumber: 1757356,
        amount: 599.00,
        quantity: 1,
        color: "green",
        pictureId: 5
      }];
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
