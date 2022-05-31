import {Contact} from "../../models";
import {ReplaySubject} from "rxjs";
import {BackendService} from "../backend.service";
import {Injectable} from "@angular/core";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})

export class ContactStore {
  // @ts-ignore
  contact: Contact;
  contactSubject: ReplaySubject<Contact> = new ReplaySubject<Contact>(1);

  constructor(private backendService: BackendService, private router: Router) {
  }

  createContact(contact: Contact): void {
    this.router.navigateByUrl('/index');
    this.backendService.postContact(contact).subscribe();
  }

}

