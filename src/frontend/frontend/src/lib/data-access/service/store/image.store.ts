import {BackendService} from "../backend.service";
import {Subject} from "rxjs";


declare var imageType: typeof Image;

export class ImageStore {


  images: Map<string, Blob> = new Map<string, Blob>();

  constructor(private backendService: BackendService) {
  }

  loadImageByName(name: string): Subject<Blob> {
    const imageSubject: Subject<Blob> = new Subject<Blob>();
    let image = this.images.get(name);
    if (image == undefined) {
      this.backendService.getImageByName(name).subscribe(imageBlob => {
        this.images.set(name, imageBlob);
        imageSubject.next(imageBlob);
      })
    } else {
      imageSubject.next(image);
    }
    return imageSubject;
  }
}
