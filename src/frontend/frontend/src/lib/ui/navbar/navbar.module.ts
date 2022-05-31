import {NgModule} from "@angular/core";
import {NavbarComponent} from "./navbar.component";
import {AppRoutingModule} from "../../../app/app-routing.module";

@NgModule({
  declarations: [NavbarComponent],
  imports: [
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [],
  exports: [NavbarComponent]
})

export class NavbarModule {

}
