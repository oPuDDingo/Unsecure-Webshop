import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {SpecifiedItem} from "src/lib/data-access/models";

@Component({
  selector: 'specified-item-list',
  templateUrl: './specifiedItemList.component.html',
  styleUrls: ['./specifiedItemList.component.scss']
})
export class SpecifiedItemListComponent {
  @Input() showDeleteButton: boolean = true;
  @Input() editableQuantity: boolean = true;
  @Input() itemList: SpecifiedItem[];

  @Output() onDeleteEvent: EventEmitter<number> = new EventEmitter<number>();
  @Output() onQuantityChangeEvent: EventEmitter<number> = new EventEmitter<number>();

  URL = "";

  constructor() {
    this.itemList = [{id: 1, name: "Iphone 11", storage: 128, articleNumber: 23424342, amount: 1299.99, quantity: 1, color: "blue", picture: "d"},
      {id: 2, name: "Iphone 10", storage: 128, articleNumber: 41561651, amount: 899.99, quantity: 2, color: "white", picture: "d"},
      {id: 3, name: "Iphone 13", storage: 256, articleNumber: 724345, amount: 1499.99, quantity: 1, color: "black", picture: "d"},
      {id: 4, name: "Samsung S22", storage: 128, articleNumber: 9873198, amount: 800.00, quantity: 4, color: "red", picture: "d"},
      {id: 5, name: "Iphone 7", storage: 512, articleNumber: 1757356, amount: 599.00, quantity: 1, color: "green", picture: "d"}];

  }

  onItemDelete(): void {

  }

  onItemChange(): void {

  }



}
