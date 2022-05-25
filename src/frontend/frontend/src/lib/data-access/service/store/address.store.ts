import {Injectable} from "@angular/core";
import {BackendService} from "../backend.service";
import {Subject} from "rxjs";
import {Address} from "../../models/address";

@Injectable({
  providedIn: 'root'
})
export class AddressStore {

  // @ts-ignore
  addresses: Address[];
  addressesSubject: Subject<Address[]> = new Subject<Address[]>();

  constructor(private backendService: BackendService) {

  }

  createAddress(address: Address): void {
    this.backendService.createAddress(address).subscribe(address => this.addresses.push(address));
  }

  getAddress(id: number): Subject<Address> {
    let addressSubject: Subject<Address> = new Subject<Address>();
    let index = this.addresses.findIndex(address => address.id === id);
    if (index === -1) {
      this.backendService.loadAddressById(id).subscribe(address => {
        addressSubject.next(address);
      });
    } else {
      addressSubject.next(this.addresses[index]);
    }
    return addressSubject;
  }

  getAllAddresses(): Subject<Address[]> {
    if (this.addresses == undefined) {
      this.backendService.loadAllAddresses().subscribe(addresses => {
        console.log(addresses);
        this.addresses = addresses;
        this.addressesSubject.next(this.addresses);
      });
    } else {
      console.log("else")
      this.addressesSubject.next(this.addresses);
    }
    return this.addressesSubject;
  }

  updateAddress(address: Address): void {
    let index = this.addresses.findIndex(item => item.id === address.id);
    this.addresses[index] = address;
    this.backendService.updateAddress(address).subscribe();
  }


}
