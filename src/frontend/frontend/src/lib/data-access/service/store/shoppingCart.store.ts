import {ReplaySubject} from "rxjs";
import {BackendService} from "../backend.service";
import {SpecifiedItem} from "../../models/specifiedItem";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartStore {
  // @ts-ignore
  itemList: SpecifiedItem[];
  itemListSubject: ReplaySubject<SpecifiedItem[]> = new ReplaySubject<SpecifiedItem[]>(1);

  constructor(private backendService: BackendService) {
  }

  getShoppingCart(): ReplaySubject<SpecifiedItem[]> {
    if (this.itemList == undefined) {
      this.backendService.getShoppingCart().subscribe(items => {
        this.itemList = items;
        this.itemListSubject.next(this.itemList);
      });
    } else {
      this.itemListSubject.next(this.itemList);
    }
    return this.itemListSubject;
  }

  updateItem(item: SpecifiedItem): ReplaySubject<SpecifiedItem[]> {
    let index = this.itemList.findIndex(i => i.id === item.id);
    this.itemList[index] = item;
    this.itemListSubject.next(this.itemList);
    this.backendService.updateShoppingCartItem(index, item).subscribe();
    return this.itemListSubject;
  }

  addItem(item: SpecifiedItem): ReplaySubject<SpecifiedItem[]> {
    this.itemList.push(item);
    this.itemListSubject.next(this.itemList);
    this.backendService.addShoppingCartItem(item).subscribe();
    return this.itemListSubject;
  }
}
