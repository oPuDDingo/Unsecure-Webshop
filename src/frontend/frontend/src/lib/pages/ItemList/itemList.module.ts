import {ItemListComponent} from "./itemList.component";
import {NgModule} from "@angular/core";
import {PageItemComponent} from "./pageItem/pageItem.component";
import {ColorCircleComponent} from "../../shared/colorCircle/colorCircle.component";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [ItemListComponent, PageItemComponent, ColorCircleComponent],
  imports: [CommonModule, RouterModule],
  providers: [],
  bootstrap: [],
  exports: [ItemListComponent]
})
export class ItemListModule {
}
