import {Component, Input} from '@angular/core';

@Component({
  selector: 'page-item',
  templateUrl: './pageItem.component.html',
  styleUrls: ['./pageItem.component.scss']
})
export class PageItemComponent {
  // @ts-ignore
  @Input() articleId;

}
