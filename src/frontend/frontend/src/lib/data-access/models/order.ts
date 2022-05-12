import {Item} from "./item";
import {Address} from "./address";
import {Payment} from "./payment";

export interface Order {
  id: number,
  order_number: number,
  items: Item[],
  address?: Address,
  amount?: number,
  payment?: Payment,
  date: String
}
