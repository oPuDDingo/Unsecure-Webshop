import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AccordionModule} from 'ngx-bootstrap/accordion';
import {ShoppingCartComponent} from "./shoppingCart.component";
import {SpecifiedItemListModule} from "../../shared/specifiedItemList/specifiedItemList.module";
import {RouterModule, Routes} from "@angular/router";
import {BoardComponent} from "../board/board.component";


const routes: Routes = [
  {path: '', component: BoardComponent}
]

@NgModule({
  declarations: [
    ShoppingCartComponent
  ],
  imports: [
    BrowserModule,
    AccordionModule.forRoot(),
    SpecifiedItemListModule,
    RouterModule.forChild(routes)
  ],
  providers: [],
  bootstrap: [],
  exports: [ShoppingCartComponent]
})
export class ShoppingCartModule {
}
