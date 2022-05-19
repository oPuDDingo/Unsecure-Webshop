import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {ArticleOverviewModule} from "../lib/pages/articleOverview/articleOverview.module";
import {HttpClientModule} from "@angular/common/http";
import {OrderProcessModule} from "../lib/pages/orderProcess/orderProcess.module";
import { TabsModule } from 'ngx-bootstrap/tabs';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ArticleOverviewModule,
    OrderProcessModule,
    TabsModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
