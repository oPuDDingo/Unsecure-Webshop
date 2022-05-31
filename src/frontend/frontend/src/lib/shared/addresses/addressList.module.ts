import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {FormsModule} from "@angular/forms";
import {AddressListComponent} from "./addressList.component";
import {ModalModule} from "ngx-bootstrap/modal";
import {AddressComponent} from "./address/address.component";

@NgModule({
  declarations: [AddressListComponent, AddressComponent],
  imports: [
    BrowserModule,
    FormsModule,
    ModalModule.forRoot(),
  ],
  providers: [],
  bootstrap: [],
  exports: [AddressListComponent]
})
export class AddressListModule {
}
