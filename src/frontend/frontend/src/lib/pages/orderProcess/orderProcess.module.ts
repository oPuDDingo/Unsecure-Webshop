import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {PaymentInformationComponent} from "./paymentInformation/paymentInformation.component";
import {OrderProcessComponent} from "./orderProcess.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    OrderProcessComponent, PaymentInformationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [],
  exports: [OrderProcessComponent]
})
export class OrderProcessModule {
}
