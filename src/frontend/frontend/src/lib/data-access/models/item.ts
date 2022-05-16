export interface Item {
  articelNumber: number;
  name: string;
  amount: number;
  picture: string;
  modelName?: string;
  brand?: string;
  operatingSystem?: string;
  releaseDate?: string;
  screen?: string;
  resolution?: string;
  pictures?: string[]; // TODO wie werden die Bilder als Array gespeichert?
}
