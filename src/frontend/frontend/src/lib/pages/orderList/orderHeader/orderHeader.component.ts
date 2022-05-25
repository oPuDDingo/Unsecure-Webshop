import {Component, Input} from '@angular/core';
import {SpecifiedItem} from "../../../data-access/models";

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'order-header',
  templateUrl: './orderHeader.component.html',
  styleUrls: ['./orderHeader.component.scss']
})
export class OrderHeaderComponent {
  // @ts-ignore
  @Input() itemList: SpecifiedItem[];
  // @ts-ignore
  @Input() orderDate: String;

  isOpen: boolean = false;

  toggleIsOpen() {
    this.isOpen = !this.isOpen;
  }

}
