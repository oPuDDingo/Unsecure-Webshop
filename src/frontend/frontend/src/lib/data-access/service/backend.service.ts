import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {Observable, tap} from "rxjs";
import {JsonObject} from "@angular/compiler-cli/ngcc/src/packages/entry_point";
import {Address} from "../models/address";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  readonly url: string = 'http://localhost:4200/api/';

  constructor(private httpClient: HttpClient) {
  }

  loadAddressById(id: number): Observable<Address> {
    return this.httpClient.get<Address>(this.url + 'user/addresses/' + id);
  }

  loadAllAddresses(): Observable<Address[]> {
    return this.httpClient.get<Address[]>(this.url + 'user/addresses');
  }

  updateAddress(address: Address): Observable<any> {
    let addressPayload = {
      ...address
    };
    return this.httpClient.put(this.url + 'user/addresses/' + address.id, addressPayload);
  }

  loadUser(): Observable<User> {
    return this.httpClient.get<User>(this.url + 'user/information');
  }

  updateUser(userPayload: JsonObject): Observable<any> {
    console.log(userPayload);
    return this.httpClient.put(this.url + 'user/information', userPayload, {observe: "response"})
      .pipe(
        tap(resp => {
          console.log(resp);
          if (resp.status == 400) {
            throw new Error('Bad request!')
          } else {
            return resp.body;
          }
        })
      );
  }

}
