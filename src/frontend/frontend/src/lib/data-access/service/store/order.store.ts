import {Order} from "../../models/order";
import {ReplaySubject} from "rxjs";
import {BackendService} from "../backend.service";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class OrderStore {
  // @ts-ignore
  orders: Order[] = [];
  ordersSubject: ReplaySubject<Order> = new ReplaySubject<Order>(1);

  constructor(private backendService: BackendService) {
  }

  getOrder(): ReplaySubject<Order> {
    return this.ordersSubject;
  }

  loadOrderById(id: number): ReplaySubject<Order> {
    const orderSubject: ReplaySubject<Order> = new ReplaySubject<Order>(1);
    let index: number = this.orders.findIndex(o => o.orderNumber === id);
    if (index == -1) {
      this.backendService.getOrder(id).subscribe(order => {
        orderSubject.next(order);
        this.orders.push(order);
      })
    } else {
      orderSubject.next(this.orders[index]);
    }
    return orderSubject;
  }
}
