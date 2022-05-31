import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {AccordionModule} from 'ngx-bootstrap/accordion';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterModule} from "@angular/router";
import {BoardModule} from "../lib/modules/board/board.module";
import {NavbarModule} from "../lib/modules/navbar/navbar.module";
import {NavbarItemsModule} from "../lib/modules/navbarItems/navbarItems.module";
import {FooterModule} from "../lib/modules/footer/footer.module";
import {NachhaltigModule} from "../lib/modules/nachhaltig/nachhaltig.module";
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AccordionModule.forRoot(),
    BrowserAnimationsModule,
    RouterModule,
    BoardModule,
    NavbarModule,
    NavbarItemsModule,
    FooterModule,
    NachhaltigModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
