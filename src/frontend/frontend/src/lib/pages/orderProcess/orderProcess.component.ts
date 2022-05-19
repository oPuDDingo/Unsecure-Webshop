import {Component, ViewChild} from "@angular/core";
import {TabsetComponent} from "ngx-bootstrap/tabs";

@Component({
  selector: 'order-process',
  templateUrl: './orderProcess.component.html',
  styleUrls: ['./orderProcess.component.scss']
})
export class OrderProcessComponent {

  @ViewChild('staticTabs', {static: false}) staticTabs?: TabsetComponent;

  disableEnable() {
    if (this.staticTabs?.tabs[2]) {
      this.staticTabs.tabs[2].disabled = !this.staticTabs.tabs[2].disabled;
    }
  }

}
