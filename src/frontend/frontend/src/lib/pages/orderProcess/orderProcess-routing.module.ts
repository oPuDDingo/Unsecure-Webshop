import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OrderProcessComponent} from "./orderProcess.component";


const routes: Routes = [
  {
    path: '', component: OrderProcessComponent, children: []
  },
  {}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderProcessRoutingModule {
}
