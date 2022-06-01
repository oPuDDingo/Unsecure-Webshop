import {NgModule} from "@angular/core";
import {FooterComponent} from "./footer.component";
import {AppRoutingModule} from "../../../app/app-routing.module";
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [FooterComponent],
  imports: [
    AppRoutingModule, CommonModule
  ],
  providers: [],
  bootstrap: [],
  exports: [FooterComponent]
})

export class FooterModule {

}
