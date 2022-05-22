import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {ArticleOverviewModule} from "../lib/pages/articleOverview/articleOverview.module";
import {HttpClientModule} from "@angular/common/http";
import {OrderProcessModule} from "../lib/pages/orderProcess/orderProcess.module";
import { TabsModule } from 'ngx-bootstrap/tabs';
import {RouterModule, Routes} from "@angular/router";
import {PaymentInformationComponent} from "../lib/pages/orderProcess/paymentInformation/paymentInformation.component";
import {AppRoutingModule} from "./app-routing.module";

const appRoutes: Routes = [
  {
    path: 'articles/:id',
    component: ArticleOverviewModule
  },
  { path: 'orderProcess',
    component: OrderProcessModule,
    outlet: 'orderProcess',
    children: [
      {
        path: 'paymentInformation',
        component: PaymentInformationComponent,
      }
    ]}
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ArticleOverviewModule,
    OrderProcessModule,
    TabsModule.forRoot(),
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
