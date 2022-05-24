import {Component, OnInit} from "@angular/core";
import {ShoppingCartStore} from "../../data-access/service/store/shoppingCart.store";
import {Shoppingcart} from "../../data-access/models/shoppingcart";
import {Address} from "../../data-access/models/address";
import {AddressStore} from "../../data-access/service/store/address.store";

@Component({
  selector: 'order-process',
  templateUrl: './orderProcess.component.html',
  styleUrls: ['./orderProcess.component.scss']
})
export class OrderProcessComponent implements OnInit {

  // @ts-ignore
  shoppingCart: Shoppingcart;
  // @ts-ignore
  addresses: Address[];


  currentStep: number = 0;
  reachedStep: number = 0;

  constructor(private shoppingCartStore: ShoppingCartStore, private addressStore: AddressStore) {
  }

  ngOnInit() {
    this.shoppingCartStore.getShoppingCart().subscribe(shoppingCart => this.shoppingCart = shoppingCart);
    this.addressStore.getAllAddresses().subscribe(addresses => this.addresses = addresses);
  }

}
