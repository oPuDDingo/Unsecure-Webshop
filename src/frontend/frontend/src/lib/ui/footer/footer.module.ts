import {NgModule} from "@angular/core";
import {FooterComponent} from "./footer.component";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {path: '', component: FooterComponent}
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
