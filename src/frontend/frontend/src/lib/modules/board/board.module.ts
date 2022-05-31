import {NgModule} from "@angular/core";
import {BoardComponent} from "./board.component";
import {FrontItemComponent} from "./frontItem/frontItem.component";
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [BoardComponent, FrontItemComponent],
  imports: [
    CommonModule
  ],
  providers: [],
  bootstrap: [],
  exports: [BoardComponent]
})

export class BoardModule {

}
