import {ContactformComponent} from "./contactform.component.";
import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    ContactformComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [],
  exports: [ContactformComponent]
})
export class ContactformModule {
}
