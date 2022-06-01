import {NgModule} from "@angular/core";
import {NavbarComponent} from "./navbar.component";
import {AppRoutingModule} from "../../../app/app-routing.module";
import {CommonModule} from "@angular/common";


@NgModule({
  declarations: [NavbarComponent],
  imports: [
    AppRoutingModule, CommonModule
  ],
  providers: [],
  bootstrap: [],
  exports: [NavbarComponent]
})

export class NavbarModule {

}
