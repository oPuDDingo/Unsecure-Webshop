import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Contact} from "../models";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient) {
  }

  postContact(contact: Contact): Observable<Contact> {
    let contactPayload = {
      "firstName": contact.firstName,
      "lastName": contact.lastName,
      "mail": contact.mail,
      "message": contact.message
    };
    return this.httpClient.post<Contact>(this.url + 'contact', contactPayload);
  }
}
