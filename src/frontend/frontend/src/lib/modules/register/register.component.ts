import {Component} from "@angular/core";
import {AuthenticationService} from "../../data-access/service/authentication.service";


@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  firstName: string = "";
  lastName: string = "";
  mail: string = "";
  password: string = "";
  passwordWdh: string = "";
  invalidData: boolean = false;

  constructor(private authenticationService: AuthenticationService) {
  }

  onRegister() {
    if (this.password != this.passwordWdh) {
      this.invalidData = true;
    } else {
      this.authenticationService.register(this.firstName, this.lastName, this.mail, this.password);
    }
  }
}
