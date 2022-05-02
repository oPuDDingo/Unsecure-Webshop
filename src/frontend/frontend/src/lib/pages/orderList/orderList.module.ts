import {NgModule} from "@angular/core";
import {AppComponent} from "../../../app/app.component";
import {OrderListComponent} from "./orderList.component";
import {BrowserModule} from "@angular/platform-browser";
import {AccordionModule} from "ngx-bootstrap/accordion";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {OrderHeaderComponent} from "./orderHeader/orderHeader.component";
import {OrderBodyComponent} from "./orderBody/orderBody.component";

@NgModule({
    declarations: [
        OrderListComponent, OrderHeaderComponent, OrderBodyComponent
    ],
  imports: [
    BrowserModule,
    AccordionModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [],
  exports: [OrderListComponent]
})
export class OrderListModule { }
