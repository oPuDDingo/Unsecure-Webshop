import {NgModule} from "@angular/core";
import {OrderListComponent} from "./orderList.component";
import {AccordionModule} from "ngx-bootstrap/accordion";
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
    AccordionModule.forRoot(),
    RouterModule.forChild(routes),
    CommonModule
  ],
  providers: [],
  exports: [OrderListComponent]
})
export class OrderListModule {
}
