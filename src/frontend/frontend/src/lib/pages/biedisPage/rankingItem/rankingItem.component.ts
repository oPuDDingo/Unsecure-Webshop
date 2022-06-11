import {Component, Input, OnInit} from "@angular/core";
import {Article, RankingStudent} from "../../../data-access/models";
import {ImageStore} from "../../../data-access/service/store/image.store";
import {BackendService} from "../../../data-access/service/backend.service";

@Component({
  selector: 'front-item',
  templateUrl: './rankingItem.component.html',
  styleUrls: ['./rankingItem.component.scss']
})

export class RankingItemComponent{
  // @ts-ignore
  @Input() rankingStudent: RankingStudent;
}
