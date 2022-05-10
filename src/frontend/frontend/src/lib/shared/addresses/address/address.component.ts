import {Component, Input, TemplateRef} from "@angular/core";
import {Address} from "../../../data-access/models/address";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {AddressStore} from "../../../data-access/service/store/address.store";

@Component({
  selector: 'address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent {
  // @ts-ignore
  @Input() address: Address;
  @Input() selectable: boolean = false;
  @Input() selected: boolean = false;

  modalRef?: BsModalRef;

  constructor(private modalService: BsModalService,
              private addressStore: AddressStore) {
  }

  onEditAddressEvent(template: TemplateRef<any>): void {
    this.modalRef = this.modalService.show(template, {animated: true, keyboard: true});
  }

  onCancel(): void {
    this.addressStore.getAddress(this.address.id).subscribe(address => this.address = address);
    this.modalRef?.hide();
  }

  onSubmit(): void {
    this.addressStore.updateAddress(this.address);
  }
}
