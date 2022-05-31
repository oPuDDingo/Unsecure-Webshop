import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ContactformComponent} from "../lib/pages/contactform/contactform.component.";


const routes: Routes = [
  {path: 'articles/:id', loadChildren: () => import('../lib/pages/articleOverview/articleOverview.module').then(m => m.ArticleOverviewModule)},
  {path: 'contact', component: ContactformComponent},
  {path: 'articles', loadChildren: () => import('../lib/pages/ItemList/itemList.module').then(m => m.ItemListModule)},
  {path: 'orders', loadChildren: () => import('../lib/pages/orderList/orderList.module').then(m => m.OrderListModule)},
  {path: 'checkout', loadChildren: () => import('../lib/pages/orderProcess/orderProcess.module').then(m => m.OrderProcessModule)},
  {path: 'cart', loadChildren: () => import('../lib/pages/shoppingCart/shoppingCart.module').then(m => m.ShoppingCartModule)},
  {path: 'user', loadChildren: () => import('../lib/pages/userSettings/userSettings.module').then(m => m.UserSettingsModule)},
  {path: 'wishlist', loadChildren: () => import('../lib/pages/wishList/wishlist.module').then(m => m.WishlistModule)},
  {path: '', redirectTo: 'articles', pathMatch: 'full'},
  {path: '**', redirectTo: 'articles'},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {enableTracing: false})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
