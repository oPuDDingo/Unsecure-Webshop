import {Component, ElementRef, OnInit, ViewChild} from "@angular/core";
import {Address, User} from "../../data-access/models";
import {UserStore} from "../../data-access/service/store/user.store";
import {AddressStore} from "../../data-access/service/store/address.store";
import {DomSanitizer, SafeHtml} from "@angular/platform-browser";

@Component({
  selector: 'user-settings',
  templateUrl: './userSettings.component.html',
  styleUrls: ['./userSettings.component.scss']
})
export class UserSettingsComponent implements OnInit {
  user: User;
  addresses: Address[] = [];

  oldPassword: string = '';
  newPassword: string = '';
  newPasswordConfirm: string = '';

  matchingNewPasswords: boolean = true;
  fulfillsPasswordRequirements: boolean = true;
  validOldPassword: boolean = true;

  // @ts-ignore
  @ViewChild('descriptionField') descriptionRef: ElementRef;

  constructor(private userStore: UserStore, private addressStore: AddressStore, private sanitizer: DomSanitizer) {
    // private imageStore: ImageStore
    // toDo: implement Image Store
    // toDo: remove hardcoded user and address

    this.user = {
      id: 1,
      mail: "test@mail.de",
      firstname: "Max",
      lastname: "Mustermann",
      newsletter: false,
      salutation: "",
      title: "",
      profilePicture: "",
      description: "test 123 <script>alert('Dont laugh, this is not a joke!')</script>"
    }
    this.addresses = [{
      id: 1,
      name: "Max Mustermann",
      country: "Deutschland",
      address: "Teststr. 4",
      address2: "1. Stock",
      zipCode: 156,
      city: "Teststadt"
    }]
  }

  ngOnInit() {
    //this.userStore.getUser().subscribe(user => this.user = user);
    //this.addressStore.getAllAddresses().subscribe(addresses => this.addresses = addresses);
  }

  printTest(): void {
    console.log(this.user.lastname);
  }

  getUserName(): string {
    return this.user.firstname + " " + this.user.lastname;
  }

  getProfileImage(): string {
    //return this.imageStore.loadImage(this.user.profilePicture);
    return "";
  }

  getDescription(): SafeHtml {
    return this.sanitizer.bypassSecurityTrustHtml(this.user.description);
  }

  validateNewPasswordEightChars(): boolean {
    return this.newPassword.length >= 8;
  }

  validateNewPasswordSpecialChars(): boolean {
    let regex = /[`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/g;
    return regex.test(this.newPassword);
  }

  validateNewPasswordOneNumber(): boolean {
    let regex = /\d+/g;
    return regex.test(this.newPassword);
  }

  onAddUserAddress(): void {
    this.addressStore.createAddress({id: 1, name: "", address: "", address2: "", city: "", zipCode: -1, country: ""});
  }

  onUpdateProfilePicture(event: any): void {
    let fileReader = new FileReader();
    fileReader.readAsDataURL(event.target.files[0]);
    fileReader.onloadend = () => {
      if (fileReader.result) {
        this.user.profilePicture = fileReader.result.toString();
        this.userStore.updateUser(this.user);
      }
    }
  }

  onSubmit(): void {
    if (this.newPassword != "") {
      if (this.newPassword != this.newPasswordConfirm) {
        this.matchingNewPasswords = false;
        return;
      } else {
        this.matchingNewPasswords = true;
      }
      if (!(this.validateNewPasswordSpecialChars()
        && this.validateNewPasswordOneNumber()
        && this.validateNewPasswordEightChars())) {
        this.fulfillsPasswordRequirements = false;
        return;
      } else {
        this.fulfillsPasswordRequirements = true;
      }
    }

    try {
      this.userStore.updateUser(this.user, this.oldPassword, this.newPassword);
      this.matchingNewPasswords = true;
      this.fulfillsPasswordRequirements = true;
      this.validOldPassword = true;
    } catch (Exception) {
      this.validOldPassword = false;
    }
  }

}
