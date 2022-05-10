import {Component, Input, OnInit} from '@angular/core';
import {Order} from "../../data-access/models/order";
import {OrderStore} from "../../data-access/service/order.store";

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'order-list',
  templateUrl: './orderList.component.html',
  styleUrls: ['./orderList.component.scss']
})
export class OrderListComponent implements OnInit {
  @Input() orderList: Order[];

  constructor(private orderStore: OrderStore) {
    this.orderList = [
      {
        id: 1, order_number: 1878869613, items: [ {id:1, name:"IPhone 12 MAX", storage:64, articleNumber:3215464, amount:132.45, quantity:1, color:"red", picture:"assets/01.png"},
          {id:2, name:"Samsung Galaxy S22 Ultra", storage:128, articleNumber:564218, amount:999.45, quantity:1, color:"grey", picture:"assets/02.png"} ],
        address: {name: "Max Mustermann", street: "Musterstraße", house_number: "420", postCode: 69420, address_suplement: "4. OG, Wohnung 20", city: "Musterhausen", country: "Germany"},
        iban: "DE458000400758", date: "12.03.2022"
      },
      {
        id: 2, order_number: 48945616547, items: [ {id:3, name:"IPhone 11 MAX", storage:128, articleNumber:231798, amount:420.69, quantity:1, color:"spacegrey", picture:"assets/03.png"}],
        date: "24.05.2020"
      }
    ];
  }

  ngOnInit() {
    this.orderStore.loadOrders().subscribe(orders => this.orderList = orders);
  }

}
