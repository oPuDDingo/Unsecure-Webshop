import {NgModule} from "@angular/core";
import {BiedisPageComponent} from "./biedisPage.component";
import {RouterModule, Routes} from "@angular/router";
import {BsDropdownModule} from "ngx-bootstrap/dropdown";
import {CommonModule} from "@angular/common";
import {RankingItemComponent} from "./rankingItem/rankingItem.component";
import {AccordionModule} from "ngx-bootstrap/accordion";
import {FormsModule} from "@angular/forms";


const routes: Routes = [
  {path: '', component: BiedisPageComponent}
]

@NgModule({
  declarations: [BiedisPageComponent, RankingItemComponent],
    imports: [RouterModule.forChild(routes), BsDropdownModule, CommonModule, AccordionModule, FormsModule],
  providers: [],
  bootstrap: [],
  exports: [BiedisPageComponent]
})

export class BiedisPageModule {

}
