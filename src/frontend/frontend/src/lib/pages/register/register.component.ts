import {Component} from "@angular/core";
import {BackendService} from "../../data-access/service/backend.service";
import {Router} from "@angular/router";


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

  constructor(private backendService: BackendService, private router: Router) {
  }

  onRegister() {
    if (this.password != this.passwordWdh) {
      this.invalidData = true;
    } else {
      this.backendService.register(this.firstName, this.lastName, this.mail, this.password).subscribe(() => this.router.navigateByUrl('/user'));
    }

  }
}
