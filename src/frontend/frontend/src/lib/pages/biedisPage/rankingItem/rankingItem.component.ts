import {Component, Input} from "@angular/core";

@Component({
  selector: 'front-item',
  templateUrl: './rankingItem.component.html',
  styleUrls: ['./rankingItem.component.scss']
})

export class RankingItemComponent{
  // @ts-ignore
  @Input() rankingStudent: RankingStudent;
}
