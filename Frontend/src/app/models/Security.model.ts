import { AssetClass } from './asset-class.model';

export interface Security {
  id?: number;
  exchange: string;
  symbol: string;
  name: string;
  isin: string;
  sector: string;
  industry: string;
  currency: string;
  country: string;
  securityCode: string;
  series: string;
  price: number | null;
  assetClass: AssetClass;
}