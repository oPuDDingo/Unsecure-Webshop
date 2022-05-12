import {Component, Input, OnInit} from "@angular/core";
import {Article} from "../../../data-access/models/article";
import {ImageStore} from "../../../data-access/service/store/image.store";

@Component({
  selector: 'article-overview-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent implements OnInit {
  // @ts-ignore
  @Input article: Article;

  constructor(private imageStore: ImageStore) {
  }

  ngOnInit() {
  }

}
