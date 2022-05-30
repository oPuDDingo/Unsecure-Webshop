import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {PaymentInformationComponent} from "./paymentInformation/paymentInformation.component";
import {OrderProcessComponent} from "./orderProcess.component";
import {FormsModule} from "@angular/forms";
import {TabsModule} from "ngx-bootstrap/tabs";
import {RouterModule} from "@angular/router";
import {OrderItemsComponent} from "./orderItems/orderItems.component";
import {ConfirmationComponent} from "./confirmation/confirmation.component";

@NgModule({
  declarations: [
    OrderProcessComponent, PaymentInformationComponent, OrderItemsComponent, ConfirmationComponent
  ],
    imports: [
        BrowserModule,
        FormsModule,
        TabsModule.forRoot(),
        RouterModule
    ],
  providers: [],
  bootstrap: [],
  exports: [OrderProcessComponent]
})
export class OrderProcessModule {
}
