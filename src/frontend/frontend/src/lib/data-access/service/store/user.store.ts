import {Injectable} from "@angular/core";
import {Subject} from "rxjs";
import {User} from "../../models";
import {BackendService} from "../backend.service";

@Injectable({
  providedIn: 'root'
})
export class UserStore {

  // @ts-ignore
  user: User;
  userSubject: Subject<User> = new Subject<User>();

  constructor(private backendService: BackendService) {
  }

  subscribeNewsletter(): void {
    this.backendService.postNewsletter();
  }
}
