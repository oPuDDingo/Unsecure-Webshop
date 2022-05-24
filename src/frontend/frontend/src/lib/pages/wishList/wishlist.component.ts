import {Component, OnInit} from "@angular/core";
import {Wishlist} from "../../data-access/models/wishlist";
import {WishlistStore} from "../../data-access/service/wishlist.store";

@Component({
  selector: 'wish-list',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.scss']
})
export class WishlistComponent implements OnInit {
  wishlist: Wishlist;
  showText: boolean = false;

  constructor(private wishlistStore: WishlistStore) {
    this.wishlist = {
      id: 1,
      itemList: [{
        id: 1,
        name: "Iphone 11",
        storage: 128,
        articleNumber: 23424342,
        amount: 1299.99,
        quantity: 1,
        color: "blue",
        picture: "d"
      },
        {
          id: 2,
          name: "Iphone 10",
          storage: 128,
          articleNumber: 41561651,
          amount: 899.99,
          quantity: 2,
          color: "white",
          picture: "d"
        },
        {
          id: 3,
          name: "Iphone 13",
          storage: 256,
          articleNumber: 724345,
          amount: 1499.99,
          quantity: 1,
          color: "black",
          picture: "d"
        },
        {
          id: 4,
          name: "Samsung S22",
          storage: 128,
          articleNumber: 9873198,
          amount: 800.00,
          quantity: 4,
          color: "red",
          picture: "d"
        },
        {
          id: 5,
          name: "Iphone 7",
          storage: 512,
          articleNumber: 1757356,
          amount: 599.00,
          quantity: 1,
          color: "green",
          picture: "d"
        }]
    }
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
    // this.wishlistStore.loadWishList().subscribe(wishlist => this.wishlist = wishlist)
  }
}
