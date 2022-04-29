import {Component, Input} from '@angular/core';
import {Item} from "../../../data-access/models/item";

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'order-header',
  templateUrl: './orderHeader.component.html',
  styleUrls: ['./orderHeader.component.scss']
})
export class OrderHeaderComponent {
  @Input() itemList: Item[];
  @Input() orderDate: String;

  isOpen:boolean = false;

  toggleIsOpen() {
    this.isOpen = !this.isOpen;
  }

}
