import {Component} from "@angular/core";
import {BackendService} from "../../data-access/service/backend.service";
import {Router} from "@angular/router";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  mail: string = "";
  password: string = "";

  constructor(private backendService: BackendService, private router: Router) {
  }

  onLogin() {
    this.backendService.login(this.mail, this.password).subscribe(() => this.router.navigateByUrl('/user'));
  }
}
