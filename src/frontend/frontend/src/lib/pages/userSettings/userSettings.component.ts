import {Component, OnInit} from "@angular/core";
import {User} from "../../data-access/models/user";
import {UserStore} from "../../data-access/service/store/user.store";
import {Address} from "../../data-access/models/address";
import {AddressStore} from "../../data-access/service/store/address.store";

@Component({
  selector: 'user-settings',
  templateUrl: './userSettings.component.html',
  styleUrls: ['./userSettings.component.scss']
})
export class UserSettingsComponent implements OnInit {
  user: User;
  addresses: Address[];

  oldPassword: string = '';
  newPassword: string = '';
  newPasswordConfirm: string = '';

  matchingNewPasswords: boolean = true;
  fulfillsPasswordRequirements: boolean = true;
  validOldPassword: boolean = true;

  constructor(private userStore: UserStore, private addressStore: AddressStore) {
    this.user = {
      id: 1,
      mail: "max.mustermann@outlook.de",
      firstname: "<script>alert('Here you go! Hacked!');</script>",
      lastname: "Mustermann",
      newsletter: true,
      salutation: "Herr",
      title: "",
      picture: "",
      description: "javascript:alert(\"Hi there\")"
    };
    this.addresses = [{
      id: 1,
      name: "Tim Nöth",
      country: "Deutschland",
      address: "Sartoriusstr 4",
      address2: "",
      zipCode: 1234156,
      city: "Würzburg"
    },
      {
        id: 2,
        name: "Tim Nöth",
        country: "Deutschland",
        address: "Sartoriusstr 4",
        address2: "",
        zipCode: 1234156,
        city: "Würzburg"
      },
      {
        id: 3,
        name: "Franzi Goll",
        country: "Frankreich",
        address: "Unter der Brücke 4",
        address2: "2. Stock",
        zipCode: 489460,
        city: "Paris"
      },
      {
        id: 4,
        name: "Valerie Morvieller",
        country: "Deutschland",
        address: "Sartoriusstr 4",
        address2: "App. 106",
        zipCode: 115648,
        city: "Würzburg"
      },
    ]
  }

  ngOnInit() {
    this.userStore.getUser().subscribe(user => this.user = user);
    this.addressStore.getAllAddresses().subscribe(addresses => this.addresses = addresses);
  }

  printTest(): void {
    console.log(this.user.lastname);
  }

  getUserName(): string {
    return this.user.firstname + " " + this.user.lastname;
  }

  getProfileImageUrl(): string {
    return location.origin + "/images/" + this.user.picture;
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
