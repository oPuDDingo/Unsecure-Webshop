import {Component, OnInit} from '@angular/core';
import {Order} from "../../data-access/models";
import {OrderStore} from "../../data-access/service/store/order.store";

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'order-list',
  templateUrl: './orderList.component.html',
  styleUrls: ['./orderList.component.scss']
})
export class OrderListComponent implements OnInit {
  // @ts-ignore
  orderList: Order[];
  showBody: boolean = false;

  constructor(private orderStore: OrderStore) {
  }

  ngOnInit() {
    this.orderStore.loadOrders().subscribe(orders => {
      this.orderList = orders;
    });
  }

}
