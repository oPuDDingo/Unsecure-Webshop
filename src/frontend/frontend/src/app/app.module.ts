import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { AccordionModule } from 'ngx-bootstrap/accordion';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {NavbarComponent} from "../lib/modules/navbar/navbar.component";
import {NavbarItemsComponent} from "../lib/modules/navbar.items/navbar.items.component";

@NgModule({
    declarations: [
        AppComponent,
        NavbarComponent,
        NavbarItemsComponent
    ],
  imports: [
    BrowserModule,
    ModalModule.forRoot(),
    AccordionModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
