import {Component, OnInit} from "@angular/core";
import {WishlistStore} from "../../data-access/service/store/wishlist.store";
import {SpecifiedItem} from "../../data-access/models";

@Component({
  selector: 'wish-list',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.scss']
})
export class WishlistComponent implements OnInit {
  itemList: SpecifiedItem[] = [];
  showText: boolean = false;

  constructor(private wishlistStore: WishlistStore) {
    // this.itemList = [{
    //   id: 1,
    //   articleNumber: 23424342,
    //   name: "Iphone 11",
    //   quantity: 1,
    //   gbSize: 128,
    //   color: "blue",
    //   amount: 1299.99,
    //   pictureId: 1
    // },
    //   {
    //     id: 2,
    //     articleNumber: 41561651,
    //     name: "Iphone 10",
    //     quantity: 2,
    //     gbSize: 128,
    //     color: "white",
    //     amount: 899.99,
    //     pictureId: 2
    //   },
    //   {
    //     id: 3,
    //     articleNumber: 724345,
    //     name: "Iphone 13",
    //     gbSize: 256,
    //     quantity: 1,
    //     amount: 1499.99,
    //     color: "black",
    //     pictureId: 3
    //   },
    //   {
    //     id: 4,
    //     articleNumber: 9873198,
    //     name: "Samsung S22",
    //     gbSize: 128,
    //     quantity: 4,
    //     amount: 800.00,
    //     color: "red",
    //     pictureId: 4
    //   },
    //   {
    //     id: 5,
    //     name: "Iphone 7",
    //     gbSize: 512,
    //     articleNumber: 1757356,
    //     amount: 599.00,
    //     quantity: 1,
    //     color: "green",
    //     pictureId: 5
    //   }];
  }

  onWishListDelete(): void {
    this.wishlistStore.deleteWishlist();
  }

  onItemDelete(itemId: number): void {
    this.wishlistStore.deleteItem(itemId);
  }

  onItemChange(event: any): void {
    this.wishlistStore.updateItem(event.itemId, event.quantity)
  }

  ngOnInit(): void {
    this.wishlistStore.loadWishList().subscribe(itemList => this.itemList = itemList)
  }
}
