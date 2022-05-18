import {Injectable} from "@angular/core";
import {User} from "../../models";
import {BackendService} from "../backend.service";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserStore {

  // @ts-ignore
  user: User;
  userSubject: Subject<User> = new Subject<User>();

  constructor(private backendService: BackendService) {
  }

  getUser(): Subject<User> {
    if (this.user == undefined) {
      this.backendService.loadUser().subscribe(user => this.user = user);
      this.userSubject.next(this.user);
    } else {
      this.userSubject.next(this.user);
    }
    return this.userSubject;
  }

  updateUser(user: User, oldPassword: string = "", newPassword: string = ""): void {
    this.user = user;
    this.userSubject.next(this.user);
    let userPayload: any = {
      ...user
    }
    if (oldPassword != "" && newPassword != "") {
      userPayload = {
        ...user,
        "oldPassword": oldPassword,
        "newPassword": newPassword
      }
    }
    // this.backendService.updateUser(userPayload).subscribe();
  }


}
