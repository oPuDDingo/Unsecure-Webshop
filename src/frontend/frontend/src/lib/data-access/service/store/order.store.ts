import {Order} from "../../models/order";
import {ReplaySubject, Subject} from "rxjs";

export class OrderStore {
  // @ts-ignore
  order: Order;
  orderSubject: ReplaySubject<Order> = new ReplaySubject<Order>(1);

  getOrder(): ReplaySubject<Order> {
    return this.orderSubject;
  }
}
