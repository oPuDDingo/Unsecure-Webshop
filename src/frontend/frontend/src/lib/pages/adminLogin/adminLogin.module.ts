import {NgModule} from "@angular/core";
import {AdminLoginComponent} from "./adminLogin.component";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {path: '', component: AdminLoginComponent}
]

@NgModule({
  declarations: [AdminLoginComponent],
  imports: [
    FormsModule,
    CommonModule,
    RouterModule.forChild(routes)
  ],
  providers: [],
  bootstrap: [],
  exports: [AdminLoginComponent]
})

export class AdminLoginModule {

}
