import {ItemListComponent} from "./itemList.component";
import {NgModule} from "@angular/core";
import {PageItemComponent} from "./pageItem/pageItem.component";


@NgModule({
  declarations: [ItemListComponent, PageItemComponent],
  imports: [],
  providers: [],
  bootstrap: [],
  exports: [ItemListComponent]
})
export class ItemListModule {
}
