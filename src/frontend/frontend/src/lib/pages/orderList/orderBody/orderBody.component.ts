import {AfterViewInit, Component, Input} from "@angular/core";
import {Order} from "../../../data-access/models";
import {OrderStore} from "../../../data-access/service/store/order.store";

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
        amount: 1131.9,
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
      }
  }

  ngAfterViewInit() {
    this.orderStore.loadOrderById(this.orderNumber).subscribe(orderWithBody => this.order = orderWithBody);
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
