import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {Address} from "../../data-access/models";
import {AddressStore} from "../../data-access/service/store/address.store";

@Component({
  selector: 'addresses',
  templateUrl: './addressList.component.html',
  styleUrls: ['./addressList.component.scss']
})
export class AddressListComponent implements OnInit {
  @Input() addresses: Address[] = [];
  @Output() addAddressEvent: EventEmitter<Address> = new EventEmitter<Address>();

  constructor(private addressStore: AddressStore) {

  }

  ngOnInit() {
    if (this.addresses == []) {
      this.addressStore.getAllAddresses().subscribe(addresses => {
        this.addresses = addresses;
      });
    }
  }

  onAddAddress(): void {
    this.addAddressEvent.emit();
  }


}
