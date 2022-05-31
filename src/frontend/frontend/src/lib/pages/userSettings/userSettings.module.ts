import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {FormsModule} from "@angular/forms";
import {ModalModule} from "ngx-bootstrap/modal";
import {UserSettingsComponent} from "./userSettings.component";
import {AlertModule} from "ngx-bootstrap/alert";
import {AddressListModule} from "../../shared/addresses/addressList.module";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {path: '', component: UserSettingsComponent}
]

@NgModule({
  declarations: [UserSettingsComponent],
  imports: [
    BrowserModule,
    FormsModule,
    ModalModule.forRoot(),
    AlertModule.forRoot(),
    AddressListModule,
    RouterModule.forChild(routes)
  ],
  providers: [],
  bootstrap: [],
  exports: [UserSettingsComponent]
})
export class UserSettingsModule {
}
