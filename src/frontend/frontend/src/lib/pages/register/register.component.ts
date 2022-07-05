import {Component} from "@angular/core";
import {Router} from "@angular/router";
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
  fulfillsPasswordRequirements: boolean = true;
  passwordIsAlreadyUsed: boolean = false;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
  }

  onRegister() {
    this.invalidData = this.password != this.passwordWdh;
    this.fulfillsPasswordRequirements = this.validateNewPasswordEightChars() && this.validateNewPasswordSpecialChars() && this.validateNewPasswordOneNumber();
    this.passwordIsAlreadyUsed = this.validatePasswordIsAlreadyUsed();
    if (!this.invalidData && this.fulfillsPasswordRequirements && !this.passwordIsAlreadyUsed)
      this.authenticationService.register(this.firstName, this.lastName, this.mail, this.password).subscribe(() => this.router.navigateByUrl('/user'));
  }

  validatePasswordIsAlreadyUsed(): boolean {
    if (this.password == "MyPasswordIsSafe" || this.password == "test") {
      return this.passwordIsAlreadyUsed = true;
    }
    return this.passwordIsAlreadyUsed = false;
  }


  validateNewPasswordEightChars(): boolean {
    return this.password.length >= 8;
  }

  validateNewPasswordSpecialChars(): boolean {
    let regex = /[`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/g;
    return regex.test(this.password);
  }

  validateNewPasswordOneNumber(): boolean {
    let regex = /\d+/g;
    return regex.test(this.password);
  }
}
