export interface Coupon {
  name: string;
  percent: number;
  active: boolean; // TODO war glaube ich relevant für Sicherheitslücke brauchen wir des?
}
