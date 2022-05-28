import {Contact} from "../../models";
import {Subject} from "rxjs";
import {BackendService} from "../backend.service";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})

export class ContactStore {
  // @ts-ignore
  contact: Contact;
  contactSubject: Subject<Contact> = new Subject<Contact>();

  constructor(private backendService: BackendService) {
  }

  createContact(contact: Contact): void {
    this.contact = contact;
    this.contactSubject.next(this.contact);
    let contactPayload: any = {...contact}
    this.backendService.postContact(contactPayload).subscribe();
  }

}

