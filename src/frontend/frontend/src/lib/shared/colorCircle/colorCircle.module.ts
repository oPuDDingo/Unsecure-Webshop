import {NgModule} from '@angular/core';
import {ColorCircleComponent} from "./colorCircle.component";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {path: '', component: ColorCircleComponent}
]

@NgModule({
  declarations: [
    ColorCircleComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes)
  ],
  providers: [],
  bootstrap: [],
  exports: [ColorCircleComponent]
})
export class ColorCircleModule {
}
