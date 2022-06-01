import {NgModule} from '@angular/core';

import {FormsModule} from "@angular/forms";
import {AddressListComponent} from "./addressList.component";
import {ModalModule} from "ngx-bootstrap/modal";
import {AddressComponent} from "./address/address.component";
import {CommonModule} from "@angular/common";


@NgModule({
  declarations: [AddressListComponent, AddressComponent],
  imports: [
    CommonModule,
    FormsModule,
    ModalModule.forRoot()
  ],
  providers: [],
  bootstrap: [],
  exports: [AddressListComponent]
})
export class AddressListModule {
}
