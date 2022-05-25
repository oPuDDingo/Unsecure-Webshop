import {Component, Input, OnInit} from '@angular/core';
import {Order} from "../../data-access/models";
import {OrderStore} from "../../data-access/service/store/order.store";

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
        orderNumber: 1878,
        date: "12.03.2022",
        specifiedItems: [{
          id: 1,
          articleNumber: 3215464,
          name: "IPhone 12 MAX",
          quantity: 1,
          gbSize: 64,
          color: "red",
          amount: 132.45,
          pictureId: 1
        },
          {
            id: 2,
            articleNumber: 564218,
            name: "Samsung Galaxy S22 Ultra",
            quantity: 1,
            gbSize: 128,
            color: "grey",
            amount: 999.45,
            pictureId: 2
          }],
        address: {
          name: "Max Mustermann",
          address: "Musterstraße 420",
          zipCode: 69420,
          address2: "4. OG, Wohnung 20",
          city: "Musterhausen",
          country: "Germany",
          deliveryInstructions: ""
        },
        payment: {
          iban: "DE458000400758",
          bic: "",
          accountHolder: ""
        }
      },
      {
        orderNumber: 48945616547,
        date: "24.05.2020",
        specifiedItems: [{
          id: 3,
          articleNumber: 231798,
          name: "IPhone 11 MAX",
          quantity: 1,
          gbSize: 128,
          color: "spacegrey",
          amount: 420.69,
          pictureId: 3
        }]
      }
    ];
  }

  ngOnInit() {
    // this.orderStore.loadOrders().subscribe(orders => this.orderList = orders);
  }

}
