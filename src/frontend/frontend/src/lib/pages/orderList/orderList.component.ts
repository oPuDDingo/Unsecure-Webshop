import {Component, Input} from '@angular/core';
import {Order} from "../../data-access/models/order";

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'order-list',
  templateUrl: './orderList.component.html',
  styleUrls: ['./orderList.component.scss']
})
export class OrderListComponent {
  @Input() orderList: Order[];

  constructor() {
    this.orderList = [
      {
        id: 1, items: [ {id:1, name:"IPhone 12 MAX", storage:64, articleNumber:3215464, amount:132.45, quantity:1, color:"red", picture:"assets/01.png"},
          {id:2, name:"Samsung Galaxy S22 Ultra", storage:128, articleNumber:564218, amount:999.45, quantity:1, color:"grey", picture:"assets/02.png"} ],
        date: "12.03.2022"
      },
      {
        id: 2, items: [ {id:3, name:"IPhone 11 MAX", storage:128, articleNumber:231798, amount:420.69, quantity:1, color:"spacegrey", picture:"assets/03.png"}],
        date: "24.05.2020"
      }
    ];
  }

}
