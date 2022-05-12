
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import {ContactformComponent} from "../lib/pages/Contactform/contactform.component.";


@NgModule({
  declarations: [
    AppComponent, ContactformComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
