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

  constructor(private userStore: UserStore, private addressStore: AddressStore, private imageStore: ImageStore) {
    // toDo: implement Image Store
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

  getProfileImage(): Blob {
    return this.imageStore.loadImage(this.user.picture);
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
