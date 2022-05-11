import {AfterViewInit, Component, Input} from "@angular/core";
import {Order} from "../../../data-access/models/order";
import {OrderStore} from "../../../data-access/service/order.store";

@Component({
  selector: 'order-body',
  templateUrl: './orderBody.component.html',
  styleUrls: ['./orderBody.component.scss']
})
export class OrderBodyComponent implements AfterViewInit {

  // @ts-ignore
  @Input() orderNumber: number;

  order: Order;

  newDate: string = '';

  constructor(private orderStore: OrderStore) {
    this.order =
      {
        id: 1,
        order_number: 3044654687132,
        items: [{
          id: 1,
          name: "IPhone 12 MAX",
          storage: 64,
          articleNumber: 3215464,
          amount: 132.45,
          quantity: 1,
          color: "red",
          picture: "assets/01.png"
        },
          {
            id: 2,
            name: "Samsung Galaxy S22 Ultra",
            storage: 128,
            articleNumber: 564218,
            amount: 999.45,
            quantity: 1,
            color: "grey",
            picture: "assets/02.png"
          }],
        address: {
          name: "Max Mustermann",
          street: "Musterstraße",
          house_number: "420",
          postCode: 69420,
          address_suplement: "4. OG, Wohnung 20",
          city: "Musterhausen",
          country: "Germany"
        },
        amount: 3546.50,
        iban: "DE458000400758",
        bic: "GENODEF5K76",
        date: "12.03.2022"
      }
  }

  ngAfterViewInit() {
    // this.orderStore.loadOrderById(this.orderNumber).subscribe(orderWithBody => this.order = orderWithBody);
    this.updateDate();
  }

  updateDate() {
    let dateArray: string[] = this.order.date.split(".");
    let tmpDate: Date = new Date();
    tmpDate.setFullYear(parseInt(dateArray[2]), parseInt(dateArray[1]) + 1, parseInt(dateArray[0]));
    if (tmpDate.getMonth() != 0) {
      this.newDate = `${tmpDate.getDate()}.${tmpDate.getMonth().toString().padStart(2, "0")}.${tmpDate.getFullYear()}`
    } else {
      this.newDate = `${tmpDate.getDate()}.${12}.${tmpDate.getFullYear()}`
    }
  }

}
