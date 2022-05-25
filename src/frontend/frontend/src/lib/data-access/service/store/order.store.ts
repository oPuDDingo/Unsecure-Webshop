import {Order} from "../../models/order";
import {ReplaySubject} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class OrderStore {
  // @ts-ignore
  order: Order;
  orderSubject: ReplaySubject<Order> = new ReplaySubject<Order>(1);

  getOrder(): ReplaySubject<Order> {
    return this.orderSubject;
  }
}
