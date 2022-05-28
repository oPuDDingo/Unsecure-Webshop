import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {NewsletterModule} from "../lib/pages/Newsletter/newsletter.module";
import {ModalModule} from 'ngx-bootstrap/modal';
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule, NewsletterModule, ModalModule.forRoot(), HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
