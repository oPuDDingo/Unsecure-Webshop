import {Component, Input} from "@angular/core";
import {Commentary} from "../../../data-access/models";

@Component({
  selector: 'article-overview-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent {

  @Input() comment: Commentary | undefined;

  getUserName(): string {
    if (this.comment) {
      if (this.comment.firstName && this.comment.lastName) {
        return `${this.comment.firstName} ${this.comment.lastName}`;
      } else {
        return "Unbekannter Nutzer";
      }
    }
    return "";

  }


}
