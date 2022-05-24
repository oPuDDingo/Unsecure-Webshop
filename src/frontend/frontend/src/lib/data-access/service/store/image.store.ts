import {BackendService} from "../backend.service";
import {ReplaySubject} from "rxjs";
import {Injectable} from "@angular/core";
import {DomSanitizer} from "@angular/platform-browser";

@Injectable({
  providedIn: 'root'
})
export class ImageStore {

  images: Map<number, string> = new Map<number, string>();

  constructor(private backendService: BackendService, private sanitizer: DomSanitizer) {
  }

  loadImageById(id: number): ReplaySubject<string> {
    const imageSubject: ReplaySubject<string> = new ReplaySubject<string>();
    let image = this.images.get(id);
    if (image == undefined) {
      this.backendService.getImageById(id).subscribe(imageString => {
        let base64Str: string = `data:image/jpeg;base64,` + JSON.parse(imageString)["data"];
        this.images.set(id, base64Str);
        imageSubject.next(base64Str);
      })
    } else {
      // @ts-ignore
      imageSubject.next(this.images.get(id));
    }
    return imageSubject;
  }
}
