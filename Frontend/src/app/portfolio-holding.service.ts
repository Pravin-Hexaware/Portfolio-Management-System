import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PortfolioHolding } from './models/portfolio-holding.model';

@Injectable({
  providedIn: 'root'
})
export class PortfolioHoldingService {
  private baseUrl = 'http://localhost:8080/api/holdings';

  constructor(private http: HttpClient) {}

  getHoldingsByPortfolio(portfolioId: number): Observable<PortfolioHolding[]> {
    return this.http.get<PortfolioHolding[]>(`${this.baseUrl}/portfolio/${portfolioId}`);
  }

  createHolding(holding: PortfolioHolding): Observable<PortfolioHolding> {
    return this.http.post<PortfolioHolding>(this.baseUrl, holding);
  }

  deleteHolding(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
