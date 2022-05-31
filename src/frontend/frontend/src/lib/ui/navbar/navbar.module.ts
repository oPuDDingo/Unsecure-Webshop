import {NgModule} from "@angular/core";
import {NavbarComponent} from "./navbar.component";
import {AppRoutingModule} from "../../../app/app-routing.module";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {path: '', component: NavbarComponent}
]

@NgModule({
  declarations: [NavbarComponent],
  imports: [
    AppRoutingModule,
    RouterModule.forChild(routes)
  ],
  providers: [],
  bootstrap: [],
  exports: [NavbarComponent]
})

export class NavbarModule {

}
