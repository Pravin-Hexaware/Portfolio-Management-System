export interface Holding {
  id: number | null;
  securityCode: string;
  securityName?: string;
  quantity: number;
  purchasePrice: number;
  purchaseDate: string; // ISO string like '2025-08-13'
  portfolioId: number;  // Foreign key reference to portfolio
}
