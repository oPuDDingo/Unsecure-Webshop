import {Component, Input} from "@angular/core";
import {Article} from "../../../data-access/models/article";

@Component({
  selector: 'article-overview-specifications',
  templateUrl: './specifications.component.html',
  styleUrls: ['./specifications.component.scss']
})
export class SpecificationsComponent {
  // @ts-ignore
  @Input article: Article;


}
