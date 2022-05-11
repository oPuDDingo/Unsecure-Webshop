import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {OrderProcessModule} from "../lib/pages/orderProcess/orderProcess.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    OrderProcessModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
