import {Injectable} from "@angular/core";
import {BackendService} from "../backend.service";
import {Subject} from "rxjs";


@Injectable({
  providedIn: 'root'
})

export class PictureStore {

  pictures: Map<string, Blob> = new Map<string, Blob>();

  constructor(private backendService: BackendService) {
  }

  loadPictureByName(name: string): Subject<Blob> {
    const pictureSubject: Subject<Blob> = new Subject<Blob>();
    let picture = this.pictures.get(name);
    if (picture == undefined) {
      this.backendService.getPictureByName(name).subscribe(pictureBlob => {
        this.pictures.set(name, pictureBlob);
        pictureSubject.next(pictureBlob);
      })
    } else {
      pictureSubject.next(picture);
    }
    return pictureSubject;

  }
}
