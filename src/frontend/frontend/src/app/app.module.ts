import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {SpecifiedItemComponent} from "../lib/modules/specifiedItem/specifiedItem.component";
import {ColorCircleComponent} from "../lib/ui/colorCircle/colorCircle.component";
import {SpecifiedItemListComponent} from "../lib/modules/specifiedItemList/specifiedItemList.component";
import { AccordionModule } from 'ngx-bootstrap/accordion';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {DeleteButtonComponent} from "../lib/ui/deleteButton/deleteButton.component";

@NgModule({
  declarations: [
    AppComponent, SpecifiedItemComponent, ColorCircleComponent, SpecifiedItemListComponent, DeleteButtonComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AccordionModule.forRoot(),
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
