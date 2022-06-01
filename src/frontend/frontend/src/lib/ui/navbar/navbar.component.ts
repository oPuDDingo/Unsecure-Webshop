import {Component, Input} from "@angular/core";
import {BackendService} from "../../data-access/service/backend.service";
import {Router} from "@angular/router";


@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @Input()
  title?: String;

  constructor(private backendService: BackendService, private router: Router) {
  }

  onLogOut(): void {
    this.backendService.logout().subscribe();
    this.router.navigateByUrl('/index');
  }

}
