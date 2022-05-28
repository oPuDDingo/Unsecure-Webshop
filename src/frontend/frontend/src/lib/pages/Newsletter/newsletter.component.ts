import {Component, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import {UserStore} from "../../data-access/service/store/user.store";

@Component({
  selector: 'newsletter',
  templateUrl: './newsletter.component.html',
  styleUrls: ['./newsletter.component.css']
})
export class NewsletterComponent {
  modalRef?: BsModalRef;

  constructor(private modalService: BsModalService, private userStore: UserStore) {
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {animated: true, keyboard: true});

  }

  onSubmit(): void {
    this.userStore.subscribeNewsletter();
  }


}


//http://localhost:4200/
