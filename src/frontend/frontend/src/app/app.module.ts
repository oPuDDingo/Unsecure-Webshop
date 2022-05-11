import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {ArticleOverviewModule} from "../lib/pages/articleOverview/articleOverview.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    ArticleOverviewModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
