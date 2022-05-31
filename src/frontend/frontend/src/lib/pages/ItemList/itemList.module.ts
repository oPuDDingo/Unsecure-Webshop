import {ItemListComponent} from "./itemList.component";
import {NgModule} from "@angular/core";
import {PageItemComponent} from "./pageItem/pageItem.component";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {ColorCircleModule} from "../../shared/colorCircle/colorCircle.module";


@NgModule({
  declarations: [ItemListComponent, PageItemComponent],
  imports: [CommonModule, RouterModule, ColorCircleModule],
  providers: [],
  bootstrap: [],
  exports: [ItemListComponent]
})
export class ItemListModule {
}
