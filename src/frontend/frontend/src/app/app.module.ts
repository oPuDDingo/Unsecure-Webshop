import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {NavbarModule} from "../lib/ui/navbar/navbar.module";
import {FooterModule} from "../lib/ui/footer/footer.module";
import {NavbarItemsModule} from "../lib/ui/navbarItems/navbarItems.module";
import {MessageAlertsModule} from "../lib/ui/messageAlerts/messageAlerts.module";
import {BiedisPageModule} from "../lib/pages/biedisPage/biedisPage.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";


@NgModule({
  declarations: [
    AppComponent
  ],
    imports: [
        BrowserModule, AppRoutingModule, HttpClientModule, NavbarModule, FooterModule, NavbarItemsModule, MessageAlertsModule, BiedisPageModule, BrowserAnimationsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
