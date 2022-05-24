import {Injectable} from "@angular/core";
import {ReplaySubject} from "rxjs";
import {BackendService} from "./backend.service";
import {Wishlist} from "../models/wishlist";
import {SpecifiedItem} from "../models";

@Injectable({
  providedIn: 'root'
})
export class WishlistStore {
  // @ts-ignore
  wishList: Wishlist;
  wishListSubject: ReplaySubject<Wishlist> = new ReplaySubject<Wishlist>(1);

  constructor(private backendService: BackendService) {

  }

  loadWishList(): ReplaySubject<Wishlist> {
    if (this.wishList == undefined) {
      this.backendService.loadWishList().subscribe(wishList => {
        this.wishList = wishList;
        this.wishListSubject.next(this.wishList);
      });
    } else {
      this.wishListSubject.next(this.wishList)
    }
    return this.wishListSubject;
  }

  // updateItems(items: SpecifiedItem[]): ReplaySubject<Wishlist> {
  //   this.wishList.itemList = items;
  //   this.wishListSubject.next(this.wishList);
  //   this.backendService.updateWishList(this.wishList).subscribe();
  //   return this.wishListSubject;
  // }

  updateItem(itemId: number, newQuantity: number): ReplaySubject<Wishlist> {
    let index = this.wishList.itemList.findIndex(i => itemId == i.id);
    this.wishList.itemList[index].quantity = newQuantity;
    this.wishListSubject.next(this.wishList);
    this.backendService.updateWishListItem(this.wishList.itemList[index]).subscribe();
    return this.wishListSubject;
  }

  addItem(item: SpecifiedItem): ReplaySubject<Wishlist> {
    this.wishList.itemList.push(item);
    this.wishListSubject.next(this.wishList);
    this.backendService.updateWishList(this.wishList).subscribe();
    return this.wishListSubject;
  }

  deleteItem(itemId: number): ReplaySubject<Wishlist> {
    let index = this.wishList.itemList.findIndex(i => itemId == i.id);
    this.wishList.itemList.splice(index, 1);
    this.wishListSubject.next(this.wishList);
    this.backendService.deleteWishListItem(itemId).subscribe();
    return this.wishListSubject;
  }

  deleteWishlist(): ReplaySubject<Wishlist> {
    this.wishList.itemList = [];
    this.wishListSubject.next(this.wishList);
    this.backendService.deleteWishList().subscribe();
    return this.wishListSubject;
  }

}
