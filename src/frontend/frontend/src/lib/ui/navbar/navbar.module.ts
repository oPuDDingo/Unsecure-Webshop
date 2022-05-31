import {NgModule} from "@angular/core";
import {NavbarComponent} from "./navbar.component";
import {AppRoutingModule} from "../../../app/app-routing.module";
import {RouterModule, Routes} from "@angular/router";
import {BoardComponent} from "../../pages/board/board.component";

const routes: Routes = [
  {path: '', component: BoardComponent}
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
