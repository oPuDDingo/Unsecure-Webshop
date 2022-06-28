import {Component, OnInit} from "@angular/core";
import {AuthenticationService} from "../../data-access/service/authentication.service";

@Component({
  selector: 'footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})

export class FooterComponent implements OnInit{

  login: boolean = false;

  constructor(private authenticationService: AuthenticationService) {
  }
  ngOnInit() {
    this.authenticationService.getStatus().subscribe(
      status => this.login = status
    );
  }
}
