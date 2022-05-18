export interface Coupon {
  name: string;
  percent?: number; // TODO percent optional, weil bei der Bestellung nur der Name angegeben wird oder?
  active?: boolean; // TODO war glaube ich relevant für Sicherheitslücke brauchen wir des?
}
