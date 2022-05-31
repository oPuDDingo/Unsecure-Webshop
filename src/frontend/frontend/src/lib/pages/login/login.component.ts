import {Component} from "@angular/core";
import {AuthenticationService} from "../../data-access/service/authentication.service";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  mail: string = "";
  password: string = "";

  constructor(private authenticationService: AuthenticationService) {
  }

  onLogin() {
    this.authenticationService.login(this.mail, this.password);
  }
}
