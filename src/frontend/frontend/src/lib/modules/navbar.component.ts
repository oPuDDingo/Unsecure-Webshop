import {Component, Input} from "@angular/core";

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @Input()
  title: String | undefined;

  constructor() {
  }
}
