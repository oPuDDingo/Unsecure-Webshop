import {NgModule} from "@angular/core";
import {NewsletterComponent} from "./newsletter.component";
import {ModalModule} from "ngx-bootstrap/modal";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";
import {BoardComponent} from "../../pages/board/board.component";

const routes: Routes = [
  {path: '', component: BoardComponent}
]

@NgModule({
  declarations: [NewsletterComponent],
  imports: [BrowserModule,
    ModalModule.forRoot(), FormsModule,
    RouterModule.forChild(routes)],
  providers: [],
  bootstrap: [],
  exports: [NewsletterComponent]
})
export class NewsletterModule {
}
