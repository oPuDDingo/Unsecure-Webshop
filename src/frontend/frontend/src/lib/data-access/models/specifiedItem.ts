export interface SpecifiedItem {
  id?: number;
  articelNumber: number;
  name?: string;
  quantity: number;
  gbSize: number; // TODO oder soll es storage sein?
  color: string;
  amount?: number;
  picture?: string; // TODO optionals sind nur für den Post bei cart + wishlist oder sind das keine optional dann?
}
