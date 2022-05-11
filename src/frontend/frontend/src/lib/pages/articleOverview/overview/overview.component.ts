import {Component, Input} from "@angular/core";
import {Article} from "../../../data-access/models/article";

@Component({
  selector: 'article-overview-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent {
  // @ts-ignore
  @Input article: Article;


}
