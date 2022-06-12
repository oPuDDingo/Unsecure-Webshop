import {Component, Input} from "@angular/core";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../data-access/service/authentication.service";
import {BackendService} from "../../data-access/service/backend.service";


@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @Input()
  title?: String;
  login: boolean = false;

  constructor(private authenticationService: AuthenticationService, private router: Router, private backendService: BackendService) {
    if(sessionStorage.getItem('sessionKey') != null){
      this.login = true;
    }
  }

  onLogOut(): void {
    this.authenticationService.logout().subscribe();
    this.router.navigateByUrl('/index');
  }
}
