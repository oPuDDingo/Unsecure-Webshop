import {NgModule} from "@angular/core";
import {FooterComponent} from "./footer.component";
import {RouterModule, Routes} from "@angular/router";
import {BoardComponent} from "../../pages/board/board.component";

const routes: Routes = [
  {path: '', component: BoardComponent}
]

@NgModule({
  declarations: [FooterComponent],
  imports: [RouterModule.forChild(routes)],
  providers: [],
  bootstrap: [],
  exports: [FooterComponent]
})

export class FooterModule {

}
