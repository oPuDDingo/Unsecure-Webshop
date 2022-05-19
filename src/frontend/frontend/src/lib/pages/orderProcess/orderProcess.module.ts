import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {PaymentInformationComponent} from "./paymentInformation/paymentInformation.component";
import {OrderProcessComponent} from "./orderProcess.component";
import {FormsModule} from "@angular/forms";
import {TabsModule} from "ngx-bootstrap/tabs";

@NgModule({
  declarations: [
    OrderProcessComponent, PaymentInformationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    TabsModule.forRoot()
  ],
  providers: [],
  bootstrap: [],
  exports: [OrderProcessComponent]
})
export class OrderProcessModule {
}
