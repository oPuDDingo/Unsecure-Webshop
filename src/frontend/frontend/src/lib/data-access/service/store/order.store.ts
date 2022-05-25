import {Injectable} from '@angular/core';
import {Order} from "../../models";
import {ReplaySubject} from "rxjs";
import {BackendService} from "../backend.service";


@Injectable({
  providedIn: 'root'
})
export class OrderStore {

  orders: Order[] = [];
  ordersSubject: ReplaySubject<Order[]> = new ReplaySubject<Order[]>(1);
  orderWithBody: Order[] = [];

  constructor(private backendService: BackendService) {

  }

  loadOrders(): ReplaySubject<Order[]> {
    if (this.orders == []) {
      this.backendService.loadOrders().subscribe(orders => {
        this.orders = orders;
        this.ordersSubject.next(this.orders);
      });
    }
    return this.ordersSubject;
  }

  loadOrderById(orderId: number): ReplaySubject<Order> {
    const orderLocalSubject: ReplaySubject<Order> = new ReplaySubject<Order>();
    let index = this.orderWithBody.findIndex(order => order.orderNumber === orderId);
    if (index == -1) {
      this.backendService.loadOrdersWithFullBody().subscribe(order => {
        this.orderWithBody.push(order);
        orderLocalSubject.next(order);
      });
    } else {
      orderLocalSubject.next(this.orderWithBody[index]);
    }
    return orderLocalSubject;
  }


}
