import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PaymentInformationComponent} from "../lib/pages/orderProcess/paymentInformation/paymentInformation.component";
import {OrderItemsComponent} from "../lib/pages/orderProcess/orderItems/orderItems.component";
import {ArticleOverviewComponent} from "../lib/pages/articleOverview/articleOverview.component";

const routes: Routes = [
  {
    path: 'orderProcess',
    children: [
      {path: 'paymentInformation', component: PaymentInformationComponent},
      {path: 'items', component: OrderItemsComponent}
    ]
  },
  {path: 'articles/:id', component: ArticleOverviewComponent},
  {path: '', redirectTo: 'orderProcess', pathMatch: 'full'},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {enableTracing: false})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
