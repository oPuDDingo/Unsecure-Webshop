import {Injectable} from "@angular/core";
import {ShoppingCart, SpecifiedItem} from "../models";
import {ReplaySubject} from "rxjs";
import {BackendService} from "./backend.service";

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartStore {
  // @ts-ignore
  shoppingCart: ShoppingCart;
  shoppingCartSubject: ReplaySubject<ShoppingCart> = new ReplaySubject<ShoppingCart>(1);

  constructor(private backendService: BackendService) {

  }

  loadShoppingCart(): ReplaySubject<ShoppingCart> {
    if (this.shoppingCart == undefined) {
      this.backendService.loadShoppingCart().subscribe(shoppingCart => {
        this.shoppingCart = shoppingCart;
        this.shoppingCartSubject.next(this.shoppingCart);
      });
    } else {
      this.shoppingCartSubject.next(this.shoppingCart)
    }
    return this.shoppingCartSubject;
  }

  updateItems(items: SpecifiedItem[]): ReplaySubject<ShoppingCart> {
    this.shoppingCart.itemList = items;
    this.shoppingCartSubject.next(this.shoppingCart);
    this.backendService.updateShoppingCart(this.shoppingCart).subscribe();
    return this.shoppingCartSubject;
  }

  addItem(item: SpecifiedItem): ReplaySubject<ShoppingCart> {
    this.shoppingCart.itemList.push(item);
    this.shoppingCartSubject.next(this.shoppingCart);
    this.backendService.updateShoppingCart(this.shoppingCart).subscribe();
    return this.shoppingCartSubject;
  }
}
