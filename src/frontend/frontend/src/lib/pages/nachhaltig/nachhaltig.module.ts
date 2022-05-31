import {NgModule} from "@angular/core";
import {NachhaltigComponent} from "./nachhaltig.component";
import {RouterModule, Routes} from "@angular/router";


const routes: Routes = [
  {path: '', component: NachhaltigComponent}
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
