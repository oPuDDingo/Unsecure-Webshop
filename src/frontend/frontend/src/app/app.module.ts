import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {ArticleOverviewModule} from "../lib/pages/articleOverview/articleOverview.module";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ArticleOverviewModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
