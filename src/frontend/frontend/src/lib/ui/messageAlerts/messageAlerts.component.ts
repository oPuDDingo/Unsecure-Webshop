import {Component, ElementRef, OnInit, ViewChild} from "@angular/core";
import {AlertMessagesStore} from "../../data-access/service/store/alertMessages.store";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'alert-messages',
  templateUrl: './messageAlerts.component.html',
  styleUrls: ['./messageAlerts.component.scss']
})

export class MessageAlertsComponent implements OnInit {

  // @ts-ignore
  @ViewChild("alertSection") alertSectionRef: ElementRef;

  constructor(private alertStore: AlertMessagesStore, private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.alertStore.alertSubject.subscribe(alert => {
      this.toast.success(alert, "Schwachstelle gefunden!")
    });
  }

  nextMessage(): void {
    this.alertStore.addAlertMessage("test");
  }

}
