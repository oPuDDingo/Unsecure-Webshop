import {Component, Input} from "@angular/core";

@Component({
  selector: 'frontItemGroup',
  templateUrl: './frontItemGroup.component.html',
  styleUrls: ['./frontItemGroup.component.scss']
})

export class FrontItemGroupComponent {
  @Input()
  name?: String;

  @Input()
  price?: number;
}

