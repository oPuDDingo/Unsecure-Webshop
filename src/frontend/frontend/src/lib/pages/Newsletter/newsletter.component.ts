import {Component, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';

@Component({
  selector: 'newsletter',
  templateUrl: './newsletter.component.html',
  styleUrls: ['./newsletter.component.css']
})
export class NewsletterComponent {
  modalRef?: BsModalRef;

  constructor(private modalService: BsModalService) {
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {animated: true, keyboard: true});
    
  }


}


//http://localhost:4200/
