import {NgModule} from '@angular/core';

import {FormsModule} from "@angular/forms";
import {AddressListComponent} from "./addressList.component";
import {ModalModule} from "ngx-bootstrap/modal";
import {AddressComponent} from "./address/address.component";
import {RouterModule, Routes} from "@angular/router";
import {CommonModule} from "@angular/common";


const routes: Routes = [
  {path: '', component: AddressListComponent}
]

@NgModule({
  declarations: [AddressListComponent, AddressComponent],
  imports: [
    CommonModule,
    FormsModule,
    ModalModule.forRoot(),
    RouterModule.forChild(routes)
  ],
  providers: [],
  bootstrap: [],
  exports: [AddressListComponent]
})
export class AddressListModule {
}
