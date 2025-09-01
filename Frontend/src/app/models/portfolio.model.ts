import { Theme } from './theme.model';

export interface Portfolio {
  id?: number;
  name: string;
  type: string;
  currency: string;
  benchmark: string;
  exchange: string;
  initialInvestment: number;
  currentValue: number;
  rebalancingFrequency: string;
  theme: Theme;
  status: string;
}
