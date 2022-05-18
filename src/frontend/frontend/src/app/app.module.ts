import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {ModalModule} from 'ngx-bootstrap/modal';
import {AccordionModule} from 'ngx-bootstrap/accordion';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NavbarComponent} from "../lib/modules/navbar/navbar.component";
import {NavbarItemsComponent} from "../lib/modules/navbar.items/navbar.items.component";
import {FooterComponent} from "../lib/modules/footer/footer.component";
import {FrontItemGroupComponent} from "../lib/modules/frontItemGroup/frontItemGroup.component";
import {BoardComponent} from "../lib/modules/board/board.component";
import {FrontItemComponent} from "../lib/modules/frontItem/frontItem.component";
import {LoginComponent} from "../lib/modules/login/login.component";
import {RouterModule, Routes} from "@angular/router";
import {RegisterComponent} from "../lib/modules/register/register.component";

const routes: Routes = [{path: '/login', component: LoginComponent}]

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    NavbarItemsComponent,
    FooterComponent,
    FrontItemGroupComponent,
    FrontItemComponent,
    BoardComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    ModalModule.forRoot(),
    AccordionModule.forRoot(),
    BrowserAnimationsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
