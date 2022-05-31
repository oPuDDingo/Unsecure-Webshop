import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {FormsModule} from "@angular/forms";
import {AddressListComponent} from "./addressList.component";
import {ModalModule} from "ngx-bootstrap/modal";
import {AddressComponent} from "./address/address.component";
import {RouterModule, Routes} from "@angular/router";
import {BoardComponent} from "../../pages/board/board.component";


const routes: Routes = [
  {path: '', component: BoardComponent}
]

@NgModule({
  declarations: [AddressListComponent, AddressComponent],
  imports: [
    BrowserModule,
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
