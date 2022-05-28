import {NgModule} from "@angular/core";
import {NewsletterComponent} from "./newsletter.component";
import {ModalModule} from "ngx-bootstrap/modal";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [NewsletterComponent],
  imports: [BrowserModule,
    ModalModule.forRoot(), FormsModule],
  providers: [],
  bootstrap: [],
  exports: [NewsletterComponent]
})
export class NewsletterModule {
}
