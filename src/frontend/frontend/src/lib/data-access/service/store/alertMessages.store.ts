import {Injectable} from "@angular/core";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class AlertMessagesStore {
  alertSubject: Subject<string> = new Subject<string>();

  constructor() {
  }

  addAlertMessage(message: string): void {
    this.alertSubject.next(message);
  }


}
