import {Component, Input, OnInit} from "@angular/core";
import {Address} from "../../data-access/models/address";
import {AddressStore} from "../../data-access/service/store/address.store";

@Component({
  selector: 'addresses',
  templateUrl: './addressList.component.html',
  styleUrls: ['./addressList.component.scss']
})
export class AddressListComponent implements OnInit {
  @Input() addresses: Address[] = [];

  constructor(private addressStore: AddressStore) {

  }

  ngOnInit() {
    if (this.addresses == []) {
      this.addressStore.getAllAddresses().subscribe(addresses => {
        this.addresses = addresses;
      });
    }
  }


}
