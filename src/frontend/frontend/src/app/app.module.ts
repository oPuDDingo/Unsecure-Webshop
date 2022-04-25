import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {SpecifiedItemComponent} from "../lib/modules/listItem/specifiedItem.component";
import {ColorCircleComponent} from "../lib/modules/colorCircle/colorCircle.component";

@NgModule({
  declarations: [
    AppComponent, SpecifiedItemComponent, ColorCircleComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
