import {NgModule} from "@angular/core";
import {NavbarItemsComponent} from "./navbarItems.component";
import {RouterModule, Routes} from "@angular/router";
import {BoardComponent} from "../../pages/board/board.component";

const routes: Routes = [
  {path: '', component: BoardComponent}
]

@NgModule({
  declarations: [NavbarItemsComponent],
  imports: [RouterModule.forChild(routes)],
  providers: [],
  bootstrap: [],
  exports: [NavbarItemsComponent]
})

export class NavbarItemsModule {

}
