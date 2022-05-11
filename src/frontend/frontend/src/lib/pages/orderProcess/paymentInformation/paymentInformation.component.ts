import {Component, Input} from "@angular/core";

@Component({
  selector: 'payment-information',
  templateUrl: './paymentInformation.component.html',
  styleUrls: ['./paymentInformation.component.scss']
})
export class PaymentInformationComponent {
  // @ts-ignore
  @Input order;

  iban: string = "";

  updateIbanValue(): void {
    this.iban = this.iban.replace(/[^\dA-Z]/g, '').replace(/(.{4})/g, '$1 ').trim();
  }
}
