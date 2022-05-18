import {Commentary} from "./Commentary";

export interface Article {
  articelNumber: number;
  name: string; // TODO modelName == name?
  amount: number;
  brand?: string;
  stars?: number;
  operatingSystem?: string;
  releaseDate?: string;
  screen?: string;
  resolution?: string;
  pictures?: number[]; // TODO brauch ich ein picture einzeln für die Frontpage oder Itemliste?
  comments?: Commentary[];
}
