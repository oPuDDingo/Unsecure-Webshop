
import {Injectable} from '@angular/core';
import {Order} from "../models/order";
import {Subject} from "rxjs";
import {BackendService} from "./backend.service";


@Injectable({
  providedIn: 'root'
})
export class OrderStore {

  orders: Order[] = [];
  ordersSubject: Subject<Order[]> = new Subject<Order[]>();
  orderWithBody: Order[] = [];
  orderWithBodySubject: Subject<Order> = new Subject<Order>();

  constructor( private backendService: BackendService ) {

  }

  loadOrders(): Subject<Order[]> {
    if (this.orders == []) {
      this.backendService.loadOrders().subscribe(orders => {
        this.orders = orders;
        this.ordersSubject.next(this.orders);
      });
    }
    return this.ordersSubject;
  }

  loadOrderById(orderId: number): Subject<Order> {
    let index = this.orderWithBody.findIndex(order => order.id === orderId);
    if(index == -1) {
      this.backendService.loadOrdersWithFullBody().subscribe( order => {
        this.orderWithBody.push(order);
        this.orderWithBodySubject.next(order);
      });
    } else {
      this.orderWithBodySubject.next(this.orderWithBody[index]);
    }
    return this.orderWithBodySubject;
  }


}
