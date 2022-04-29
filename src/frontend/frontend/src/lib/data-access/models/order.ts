import {Item} from "./item";
import {Address} from "./address";
import {Payment} from "./payment";

export interface Order {
  id: number,
  mail?: String,
  items: Item[],
  address?: Address,
  payment?: Payment,
  date: String
}
