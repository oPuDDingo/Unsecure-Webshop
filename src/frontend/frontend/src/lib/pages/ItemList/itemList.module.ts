import {ItemListComponent} from "./itemList.component";
import {NgModule} from "@angular/core";
import {PageItemComponent} from "./pageItem/pageItem.component";
import {CommonModule} from "@angular/common";
import {RouterModule, Routes} from "@angular/router";
import {ColorCircleModule} from "../../shared/colorCircle/colorCircle.module";
import {BoardComponent} from "../board/board.component";


const routes: Routes = [
  {path: '', component: BoardComponent}
]

@NgModule({
  declarations: [ItemListComponent, PageItemComponent],
  imports: [CommonModule, RouterModule, ColorCircleModule, RouterModule.forChild(routes)],
  providers: [],
  bootstrap: [],
  exports: [ItemListComponent]
})
export class ContactFormModule {
}
