import {SpecifiedItem} from "./specifiedItem";
import {Coupon} from "./coupon";
import {Payment} from "./payment";
import {Address} from "./address";

export interface Order {
  orderNumber: number;
  date: string;
  specifiedItems: SpecifiedItem[];
  amount?: number;
  address?: Address;
  payment?: Payment;
  coupons?: Coupon[];
}
