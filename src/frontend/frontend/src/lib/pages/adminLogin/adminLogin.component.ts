import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {AdminAuthenticationService} from "../../data-access/service/adminAuthentication.service";
@Component({
  selector: 'admin-login',
  templateUrl: './adminLogin.component.html',
  styleUrls: ['./adminLogin.component.scss']
})
export class AdminLoginComponent {

  username: string = "";
  password: string = "";

  invalidData: boolean = false;

  constructor(private adminAuthenticationService: AdminAuthenticationService, private router: Router) {
  }

  onLogin() {
    this.adminAuthenticationService.login(this.username, this.password).subscribe(() => this.router.navigateByUrl('/admin'), (error) => this.invalidData = true);
  }
}
