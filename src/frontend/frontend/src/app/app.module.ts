import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {NavbarModule} from "../lib/ui/navbar/navbar.module";
import {FooterModule} from "../lib/ui/footer/footer.module";
import {NavbarItemsModule} from "../lib/ui/navbarItems/navbarItems.module";
import {MessageAlertsModule} from "../lib/ui/messageAlerts/messageAlerts.module";
import { PaginationModule } from 'ngx-bootstrap/pagination';
import {BiedisPageModule} from "../lib/pages/biedisPage/biedisPage.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {SqlInjectionModule} from "../lib/pages/securityVulnerabilityInformation/sqlInjection/sqlInjection.module";
import {
  BlindSqlInjectionModule
} from "../lib/pages/securityVulnerabilityInformation/blindSqlInjection/blindSqlInjection.module";
import {XSSModule} from "../lib/pages/securityVulnerabilityInformation/xss/xss.module";
import {
  InsecureFileUploadModule
} from "../lib/pages/securityVulnerabilityInformation/insecureFileUpload/insecureFileUpload.module";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, HttpClientModule, NavbarModule, FooterModule, NavbarItemsModule, MessageAlertsModule, PaginationModule.forRoot(), BiedisPageModule, BrowserAnimationsModule, SqlInjectionModule, BlindSqlInjectionModule, XSSModule, InsecureFileUploadModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
