import {Component, Input} from "@angular/core";
import {Article} from "../../../data-access/models/article";

@Component({
  selector: 'article-overview-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent {
  // @ts-ignore
  @Input article: Article;


}
