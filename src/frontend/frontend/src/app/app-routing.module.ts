import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ArticleOverviewComponent} from "../lib/pages/articleOverview/articleOverview.component";

const routes: Routes = [
  {
    path: 'orderProcess',
    loadChildren: () => import('../lib/pages/orderProcess/orderProcess.module').then(m => m.OrderProcessModule)
  },
  {path: 'articles/:id', component: ArticleOverviewComponent},
  {path: '', redirectTo: 'orderProcess', pathMatch: 'full'},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {enableTracing: false})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
