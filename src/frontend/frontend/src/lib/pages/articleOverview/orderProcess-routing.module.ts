import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OrderProcessComponent} from "../orderProcess/orderProcess.component";


const routes: Routes = [
  {
    path: '', component: OrderProcessComponent, children: [

      {
        path: 'casual', component: CasualComponent
      },
      {
        path: 'earned', component: EarnedComponent
      },
      {
        path: '', redirectTo: 'casual', pathMatch: 'full'
      },
      { path: '**', component:  Page404balanceComponent}
    ]
  },
  {

  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderProcessRoutingModule { }
