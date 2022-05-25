import {Shoppingcart} from "../../models/shoppingcart";
import {ReplaySubject} from "rxjs";
import {BackendService} from "../backend.service";
import {SpecifiedItem} from "../../models/specifiedItem";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartStore {
  // @ts-ignore
  shoppingCart: Shoppingcart;
  shoppingCartSubject: ReplaySubject<Shoppingcart> = new ReplaySubject<Shoppingcart>(1);

  constructor(private backendService: BackendService) {
  }

  getShoppingCart(): ReplaySubject<Shoppingcart> {
    if (this.shoppingCart == undefined) {
      this.backendService.getShoppingCart().subscribe(shoppingCart => {
        this.shoppingCart = shoppingCart;
        this.shoppingCartSubject.next(this.shoppingCart);
      });
    } else {
      this.shoppingCartSubject.next(this.shoppingCart);
    }
    return this.shoppingCartSubject;
  }

  updateItems(items: SpecifiedItem[]): ReplaySubject<Shoppingcart> {
    this.shoppingCart.itemList = items;
    this.shoppingCartSubject.next(this.shoppingCart);
    this.backendService.updateShoppingCart(this.shoppingCart).subscribe();
    return this.shoppingCartSubject;
  }

  addItem(item: SpecifiedItem): ReplaySubject<Shoppingcart> {
    this.shoppingCart.itemList.push(item);
    this.backendService.updateShoppingCart(this.shoppingCart).subscribe();
    this.shoppingCartSubject.next(this.shoppingCart);
    return this.shoppingCartSubject;
  }
}
