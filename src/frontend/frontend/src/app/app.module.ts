import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SpecifiedItemListModule } from "../lib/pages/specifiedItemList/specifiedItemList.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule, SpecifiedItemListModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
