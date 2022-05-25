import {Component, Input} from "@angular/core";
import {SpecifiedItem} from "../../../data-access/models/specifiedItem";

@Component({
  selector: 'order-items',
  templateUrl: './orderItems.component.html',
  styleUrls: ['./orderItems.component.scss']
})
export class OrderItemsComponent {

  // @ts-ignore
  @Input() itemList: SpecifiedItem[];

  constructor() {
    console.log("init OrderItems with itemList");
    // @ts-ignore
    console.log(this.itemList);
  }

}
