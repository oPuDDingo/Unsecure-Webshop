import {Component, Input} from "@angular/core";

@Component({
  selector: 'article-overview-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent {
  // @ts-ignore
  @Input comment: Commentary;

  getUserName(): string {
    if (this.comment.firstName && this.comment.lastName) {
      return `${this.comment.firstName} ${this.comment.lastName}`;
    } else {
      return "Unbekannter Nutzer";
    }
  }


}
