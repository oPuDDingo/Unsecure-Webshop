import {ContactformComponent} from "./contactform.component.";
import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {path: '', component: ContactformComponent}
]

@NgModule({
  declarations: [
    ContactformComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes)
  ],
  providers: [],
  bootstrap: [],
  exports: [ContactformComponent]
})
export class ContactformModule {
}
