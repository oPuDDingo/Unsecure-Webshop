import {NgModule} from "@angular/core";
import {NewsletterComponent} from "./newsletter.component";
import {ModalModule} from "ngx-bootstrap/modal";
import {BrowserModule} from "@angular/platform-browser";

@NgModule({
  declarations: [NewsletterComponent],
  imports: [BrowserModule,
    ModalModule.forRoot()],
  providers: [],
  bootstrap: [],
  exports: [NewsletterComponent]
})
export class NewsletterModule {
}
