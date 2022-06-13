import {Component, Input, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../data-access/service/authentication.service";
import {BackendService} from "../../data-access/service/backend.service";


@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit{
  @Input()
  title?: String;
  login: boolean = false;

  constructor(private authenticationService: AuthenticationService, private router: Router, private backendService: BackendService) {

  }

  onLogOut(): void {
    this.authenticationService.logout().subscribe();
    this.router.navigateByUrl('/index');
  }

  ngOnInit() {
    if(sessionStorage.getItem('sessionKey') != null){
      console.log("sessionKey!=null")
      this.login = true;
    }
  }


}
