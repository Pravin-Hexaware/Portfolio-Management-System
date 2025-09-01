import { Portfolio } from './portfolio.model';

// export interface PortfolioHolding {
//   id: number;
//   portfolio: Portfolio | null; 
//   quantity: number;
//   price: number;
//   value: number;
//   equityCategory: string;
//   security: {
//     id: number;
//     name: string;
//     assetClass: string;
//     subAssetClass: string;
//   };
// }


export interface PortfolioHolding {
  id?: number;
  quantity: number;
  price: number;
  value: number;
  equityCategory: string;
  security: {
    id: number;
    name?: string;
    price?: number;
    assetClass?: {
      id: number;
      className?: string;
      subClassName?: string;
    };
  };
  portfolio?: {
    id: number;
  };

  // Add these two optional fields
  currentPrice?: number;
  marketValue?: number;
}