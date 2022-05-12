import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {NewsletterModule} from "../lib/pages/Newsletter/newsletter.module";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule, NewsletterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
