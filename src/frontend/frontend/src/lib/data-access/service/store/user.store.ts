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

  /*getUser(): Subject<User> {
    if (this.user == undefined) {
      this.backendService.loadUser().subscribe(user => this.user = user);
      this.userSubject.next(this.user);
    } else {
      this.userSubject.next(this.user);
    }
    return this.userSubject;
  }*/

  subscribeNewsletter(): void {
    this.backendService.postNewsletter();
  }
}
