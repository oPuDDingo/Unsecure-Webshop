import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AccordionModule} from 'ngx-bootstrap/accordion';
import {SpecifiedItemComponent} from "./specifiedItem/specifiedItem.component";
import {ColorCircleComponent} from "../../shared/colorCircle/colorCircle.component";
import {SpecifiedItemListComponent} from "./specifiedItemList.component";
import {DeleteButtonComponent} from "../../shared/deleteButton/deleteButton.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    SpecifiedItemComponent, ColorCircleComponent, SpecifiedItemListComponent, DeleteButtonComponent
  ],
  imports: [
    BrowserModule,
    AccordionModule.forRoot(),
    FormsModule
  ],
  providers: [],
  bootstrap: [],
  exports: [SpecifiedItemListComponent]
})
export class SpecifiedItemListModule {
}
