import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AccordionModule} from 'ngx-bootstrap/accordion';
import {SpecifiedItemComponent} from "./specifiedItem/specifiedItem.component";
import {SpecifiedItemListComponent} from "./specifiedItemList.component";
import {DeleteButtonComponent} from "../deleteButton/deleteButton.component";
import {FormsModule} from "@angular/forms";
import {ColorCircleModule} from "../colorCircle/colorCircle.module";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {path: '', component: SpecifiedItemComponent}
]

@NgModule({
  declarations: [
    SpecifiedItemComponent, SpecifiedItemListComponent, DeleteButtonComponent
  ],
  imports: [
    BrowserModule,
    AccordionModule.forRoot(),
    FormsModule,
    ColorCircleModule,
    RouterModule.forChild(routes)
  ],
  providers: [],
  bootstrap: [],
  exports: [SpecifiedItemListComponent]
})
export class SpecifiedItemListModule {
}
