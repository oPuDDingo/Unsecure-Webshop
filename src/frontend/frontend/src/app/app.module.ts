import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ShoppingCartModule} from "../lib/pages/shoppingCart/shoppingCart.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule, ShoppingCartModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
