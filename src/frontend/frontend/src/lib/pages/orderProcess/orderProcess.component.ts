import {Component, OnInit} from "@angular/core";
import {ShoppingCartStore} from "../../data-access/service/store/shoppingCart.store";
import {Address} from "../../data-access/models/address";
import {AddressStore} from "../../data-access/service/store/address.store";
import {SpecifiedItem} from "../../data-access/models";

@Component({
  selector: 'order-process',
  templateUrl: './orderProcess.component.html',
  styleUrls: ['./orderProcess.component.scss']
})
export class OrderProcessComponent implements OnInit {

  // @ts-ignore
  itemsList: SpecifiedItem[];

  // @ts-ignore
  addresses: Address[];

  currentStep: number = 0;
  reachedStep: number = 0;

  constructor(private shoppingCartStore: ShoppingCartStore, private addressStore: AddressStore) {
  }

  ngOnInit() {
    this.shoppingCartStore.getShoppingCart().subscribe(items => {
      this.itemsList = items;
      console.log(this.itemsList)
    });
    this.addressStore.getAllAddresses().subscribe(addresses => this.addresses = addresses);
  }

  changeStep(selectedStep: number): void {
    this.reachedStep++;
    this.currentStep = selectedStep;
  }

  getItemList(): SpecifiedItem[] {
    return this.itemsList;
  }
}
