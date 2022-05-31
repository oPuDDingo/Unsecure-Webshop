import {NgModule} from "@angular/core";
import {NachhaltigComponent} from "./nachhaltig.component";
import {RouterModule, Routes} from "@angular/router";
import {BoardComponent} from "../board/board.component";


const routes: Routes = [
  {path: '', component: BoardComponent}
]

@NgModule({
  declarations: [NachhaltigComponent],
  imports: [RouterModule.forChild(routes)],
  providers: [],
  bootstrap: [],
  exports: [NachhaltigComponent]
})

export class NachhaltigModule {

}
