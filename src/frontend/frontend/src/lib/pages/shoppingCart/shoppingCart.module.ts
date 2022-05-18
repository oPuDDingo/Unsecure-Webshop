import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AccordionModule} from 'ngx-bootstrap/accordion';
import {ShoppingCartComponent} from "./shoppingCart.component";
import {SpecifiedItemListModule} from "../specifiedItemList/specifiedItemList.module";

@NgModule({
  declarations: [
    ShoppingCartComponent
  ],
  imports: [
    BrowserModule,
    AccordionModule.forRoot(),
    SpecifiedItemListModule
  ],
  providers: [],
  bootstrap: [],
  exports: [ShoppingCartComponent]
})
export class ShoppingCartModule {
}
