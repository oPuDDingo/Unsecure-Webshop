import {ReplaySubject} from "rxjs";
import {Injectable} from "@angular/core";
import {BackendService} from "./backend.service";

@Injectable({
  providedIn: 'root'
})
export class ImageStore {

  images: Map<number, string> = new Map<number, string>();

  constructor(private backendService: BackendService) {
  }

  loadImageById(id: number): ReplaySubject<string> {
    const imageSubject: ReplaySubject<string> = new ReplaySubject<string>();
    let image = this.images.get(id);
    if (image == undefined) {
      this.backendService.getImageById(id).subscribe(imageString => {
        let base64String = "data:image/png;base64, " + imageString;
        this.images.set(id, base64String);
        imageSubject.next(base64String);
      })
    } else {
      imageSubject.next(image);
    }
    return imageSubject;
  }
}
