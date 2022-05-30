import {NgModule} from "@angular/core";
import {PaymentInformationComponent} from "./paymentInformation/paymentInformation.component";
import {OrderProcessComponent} from "./orderProcess.component";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";
import {OrderItemsComponent} from "./orderItems/orderItems.component";
import {CommonModule} from "@angular/common";
import {SpecifiedItemListModule} from "../../shared/specifiedItemList/specifiedItemList.module";
import {ConfirmationComponent} from "./confirmation/confirmation.component";

const routes: Routes = [
  {path: 'orderProcess', component: OrderProcessComponent}
]

@NgModule({
  declarations: [
    OrderProcessComponent, PaymentInformationComponent, OrderItemsComponent, ConfirmationComponent
  ],
  imports: [
    FormsModule,
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
