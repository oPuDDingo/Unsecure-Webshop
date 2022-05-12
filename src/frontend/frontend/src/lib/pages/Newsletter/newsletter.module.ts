import {NgModule} from "@angular/core";
import {NewsletterComponent} from "./newsletter.component";
import {ModalModule} from "ngx-bootstrap/modal";

@NgModule({
  declarations: [NewsletterComponent],
  imports: [ModalModule.forRoot()],
  providers: [],
  bootstrap: [],
  exports: [NewsletterComponent]
})
export class NewsletterModule {
}
