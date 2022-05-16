import {Item} from "./item";
import {Address} from "./address";
import {Payment} from "./payment";

export interface Order {
  orderNumber: number;
  date: string;
  items: Item[];
  amount?: number;
  address?: Address[];
  payment?: Payment[]; // TODO interface für Post relevant?
  // coupons: Coupon[];    // TODO wird des benötigt?
}
