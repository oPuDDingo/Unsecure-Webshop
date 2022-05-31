import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AccordionModule} from 'ngx-bootstrap/accordion';
import {WishlistComponent} from "./wishlist.component";
import {SpecifiedItemListModule} from "../../shared/specifiedItemList/specifiedItemList.module";
import {ColorCircleModule} from "../../shared/colorCircle/colorCircle.module";

@NgModule({
  declarations: [
    WishlistComponent
  ],
  imports: [
    BrowserModule,
    AccordionModule.forRoot(),
    SpecifiedItemListModule,
    ColorCircleModule
  ],
  providers: [],
  bootstrap: [],
  exports: [WishlistComponent]
})
export class WishlistModule {
}
