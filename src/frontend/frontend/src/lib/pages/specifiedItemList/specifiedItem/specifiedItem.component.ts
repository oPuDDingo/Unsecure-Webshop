import {Component, EventEmitter, Input, Output} from "@angular/core";
import {SpecifiedItem} from "src/lib/data-access/models";

@Component({
  selector: 'specified-item',
  templateUrl: './specifiedItem.component.html',
  styleUrls: ['./specifiedItem.component.scss']
})
export class SpecifiedItemComponent {
  @Input() showDeleteButton: boolean = true;
  @Input() editableQuantity: boolean = true;
  // @ts-ignore
  @Input() specifiedItem: SpecifiedItem;

  @Output() onDeleteEvent: EventEmitter<number> = new EventEmitter<number>();
  @Output() onQuantityChangeEvent: EventEmitter<{ itemId: number, quantity: number }> = new EventEmitter<{ itemId: number, quantity: number }>();

  showTrash: boolean = false;

  onItemDelete(): void {
    this.onDeleteEvent.emit(this.specifiedItem.id);
  }

  onItemChange(event: any): void {
    this.onQuantityChangeEvent.emit({itemId: this.specifiedItem.id, quantity: event.target.value});
  }

  onMouseEnter(): void {
    this.showTrash = true;
  }

  onMouseLeave(): void {
    this.showTrash = false;
  }

  getPictureUrl(): string {
    return location.origin + '/images/' + this.specifiedItem.picture;
  }
}
