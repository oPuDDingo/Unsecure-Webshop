import {NgModule} from "@angular/core";
import {RegisterComponent} from "./register.component";
import {FormsModule} from "@angular/forms";
import {AlertModule} from "ngx-bootstrap/alert";
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [RegisterComponent],
  imports: [
    FormsModule,
    AlertModule,
    CommonModule
  ],
  providers: [],
  bootstrap: [],
  exports: [RegisterComponent]
})

export class RegisterModule {

}
