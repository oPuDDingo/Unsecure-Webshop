import {Component, OnInit} from "@angular/core";
import {Contact} from "../../data-access/models";
import {ContactStore} from "../../data-access/service/store/contact.store";

@Component(
  {
    selector: 'contactform',
    templateUrl: './contactform.component.html',
    styleUrls: ['./contactform.component.scss']
  }
)

export class ContactformComponent implements OnInit {

  // @ts-ignore
  contact: Contact = {firstName: '', lastName: '', mail: '', message: ''};

  constructor(private contactStore: ContactStore) {
  }

  onSubmit(): void {
    this.contactStore.createContact(this.contact);
  }

  ngOnInit(): void {
  }

}
