import {Component, Input} from "@angular/core";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../data-access/service/authentication.service";


@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @Input()
  title?: String;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
  }

  onLogOut(): void {
    this.authenticationService.logout().subscribe();
    this.router.navigateByUrl('/index');
  }

}
