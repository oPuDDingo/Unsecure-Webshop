import {NgModule} from "@angular/core";
import {OrderListComponent} from "./orderList.component";
import {BrowserModule} from "@angular/platform-browser";
import {AccordionModule} from "ngx-bootstrap/accordion";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {OrderHeaderComponent} from "./orderHeader/orderHeader.component";
import {OrderBodyComponent} from "./orderBody/orderBody.component";
import {RouterModule, Routes} from "@angular/router";
import {CommonModule} from "@angular/common";

const routes: Routes = [
  {path: 'orderList', component: OrderListComponent}
]

@NgModule({
  declarations: [
    OrderListComponent, OrderHeaderComponent, OrderBodyComponent
  ],
  imports: [
    BrowserModule,
    AccordionModule.forRoot(),
    BrowserAnimationsModule,
    RouterModule.forChild(routes),
    CommonModule
  ],
  providers: [],
  exports: [OrderListComponent]
})
export class OrderListModule {
}
