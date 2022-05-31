import {NgModule} from '@angular/core';
import {ColorCircleComponent} from "./colorCircle.component";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

@NgModule({
  declarations: [
    ColorCircleComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [],
  exports: [ColorCircleComponent]
})
export class ColorCircleModule {
}
