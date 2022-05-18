import {Component, EventEmitter, Input, Output} from "@angular/core";
import {ShoppingCart} from "src/lib/data-access/models";

@Component({
  selector: 'shopping-cart',
  templateUrl: './shoppingCart.component.html',
  styleUrls: ['./shoppingCart.component.scss']
})
export class ShoppingCartComponent {
  @Input() shoppingCart: ShoppingCart;
  @Input() showText: boolean = false;

  @Output() onDeleteEvent: EventEmitter<number> = new EventEmitter<number>();
  @Output() onQuantityChangeEvent: EventEmitter<{ itemId: number, quantity: number }> = new EventEmitter<{ itemId: number, quantity: number }>();

  constructor() {
    this.shoppingCart = {
      id: 1,
      itemList: [{
        id: 1,
        name: "Iphone 11",
        storage: 128,
        articleNumber: 23424342,
        amount: 1299.99,
        quantity: 1,
        color: "blue",
        picture: "d"
      },
        {
          id: 2,
          name: "Iphone 10",
          storage: 128,
          articleNumber: 41561651,
          amount: 899.99,
          quantity: 2,
          color: "white",
          picture: "d"
        },
        {
          id: 3,
          name: "Iphone 13",
          storage: 256,
          articleNumber: 724345,
          amount: 1499.99,
          quantity: 1,
          color: "black",
          picture: "d"
        },
        {
          id: 4,
          name: "Samsung S22",
          storage: 128,
          articleNumber: 9873198,
          amount: 800.00,
          quantity: 4,
          color: "red",
          picture: "d"
        },
        {
          id: 5,
          name: "Iphone 7",
          storage: 512,
          articleNumber: 1757356,
          amount: 599.00,
          quantity: 1,
          color: "green",
          picture: "d"
        }]
    }
  }

  onItemDelete(itemId: number): void { // TODO muss ich das weitergeben oder hier verarbeiten?
    this.onDeleteEvent.emit(itemId);
  }

  onItemChange(event: any): void {
    this.onQuantityChangeEvent.emit(event);
  }
}
