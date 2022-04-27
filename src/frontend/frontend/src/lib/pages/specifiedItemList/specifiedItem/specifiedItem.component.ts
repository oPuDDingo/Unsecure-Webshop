import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {SpecifiedItem} from "src/lib/data-access/models";

@Component({
    selector: 'specified-item',
    templateUrl: './specifiedItem.component.html',
    styleUrls: ['./specifiedItem.component.scss']
})
export class SpecifiedItemComponent {
    @Input() showDeleteButton: boolean = true;
    @Input() editableQuantity: boolean = true;
    @Input() specifiedItem: SpecifiedItem;

    @Output() onDeleteEvent: EventEmitter<number> = new EventEmitter<number>();
    @Output() onQuantityChangeEvent: EventEmitter<number> = new EventEmitter<number>();

    showTrash: boolean = false;

    URL = "";

    constructor() {
        this.specifiedItem = {id: 1, name: "Iphone 11", storage: 128, articleNumber: 23424342, amount: 1299.99, quantity: 1, color: "blue", picture: "d"};

    }

    onItemDelete(): void {

    }

    onItemChange(): void {

    }

    onMouseEnter( ): void {
      this.showTrash = true;
    }

    onMouseLeave( ): void {
      this.showTrash = false;
    }
}
