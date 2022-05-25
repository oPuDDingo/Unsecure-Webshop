import {NgModule} from "@angular/core";
import {PaymentInformationComponent} from "./paymentInformation/paymentInformation.component";
import {OrderProcessComponent} from "./orderProcess.component";
import {FormsModule} from "@angular/forms";
import {TabsModule} from "ngx-bootstrap/tabs";
import {RouterModule, Routes} from "@angular/router";
import {OrderItemsComponent} from "./orderItems/orderItems.component";
import {CommonModule} from "@angular/common";
import {SpecifiedItemListModule} from "../specifiedItemList/specifiedItemList.module";

const routes: Routes = [
  {path: 'orderProcess', component: OrderProcessComponent}
]

@NgModule({
  declarations: [
    OrderProcessComponent, PaymentInformationComponent, OrderItemsComponent
  ],
  imports: [
    FormsModule,
    TabsModule.forRoot(),
    RouterModule.forChild(routes),
    CommonModule,
    SpecifiedItemListModule
  ],
  providers: [],
  bootstrap: [],
  exports: [RouterModule]
})
export class OrderProcessModule {
}
