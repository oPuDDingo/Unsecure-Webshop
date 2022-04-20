import {Component, EventEmitter, Input, Output} from "@angular/core";
import {SpecifiedItem} from "../../data-access/models/specifiedItem";

@Component({
    selector: 'listItem',
    templateUrl: './listItem.component.html',
    styleUrls: ['./listItem.component.scss']
})
export class ListItemComponent {
    @Input() showDeleteButton: boolean;
    @Input() editableQuantity: boolean;
    @Input() specifiedItem: SpecifiedItem;

    @Output() onDeleteEvent: EventEmitter<number> = new EventEmitter<number>();
    @Output() onQuantityChangeEvent: EventEmitter<number> = new EventEmitter<number>();

    URL = "";

    onItemDelete(): void {

    }

    onItemChange(): void {

    }

}
