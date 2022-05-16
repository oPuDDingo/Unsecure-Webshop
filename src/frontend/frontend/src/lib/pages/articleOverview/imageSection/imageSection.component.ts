import {Component, Input, OnInit} from "@angular/core";
import {Article} from "../../../data-access/models/article";
import {ImageStore} from "../../../data-access/service/store/image.store";

@Component({
  selector: 'article-overview-images',
  templateUrl: './imageSection.component.html',
  styleUrls: ['./imageSection.component.scss']
})
export class ImageSectionComponent implements OnInit {
  // @ts-ignore
  @Input article: Article;

  images: Map<string, Blob> = new Map<string, Blob>();

  constructor(private imageStore: ImageStore) {
  }

  ngOnInit() {
    this.article.picture.forEach(name => {
      this.imageStore.loadImageByName(name).subscribe(image => {
        this.images.set(name, image);
      });
    });
  }

  getImage(name: string): Blob {
    // @ts-ignore
    return this.images.get(name);
  }

}
