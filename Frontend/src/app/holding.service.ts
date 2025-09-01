import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PortfolioHolding } from './models/portfolio-holding.model';
import { Portfolio } from './models/portfolio.model';

@Injectable({
  providedIn: 'root'
})
export class HoldingService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getPortfolio(id: number): Observable<Portfolio> {
    return this.http.get<Portfolio>(`${this.baseUrl}/portfolios/${id}`);
  }

  // getHoldingsByPortfolioId(id: number): Observable<PortfolioHolding[]> {
  //   return this.http.get<PortfolioHolding[]>(`${this.baseUrl}/portfolios/${id}/holdings`);
  // }

  getHoldingsByPortfolioId(portfolioId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/holdings/portfolio/${portfolioId}`);
  }


getSecurities(): Observable<any[]> {
  return this.http.get<any[]>(`${this.baseUrl}/securities`);
}

submitHolding(holding: any): Observable<any> {
  return this.http.post(`${this.baseUrl}/holdings`, holding);
}

deleteHolding(id: number): Observable<void> {
  return this.http.delete<void>(`http://localhost:8080/api/holdings/${id}`);
}

getSecurityById(id: number): Observable<any> {
  return this.http.get<any>(`${this.baseUrl}/securities/${id}`);
}

updatePortfolio(portfolio: any): Observable<any> {
  return this.http.put(`${this.baseUrl}/portfolios/${portfolio.id}`, portfolio);
}

getThemeAllocations(themeId: number): Observable<any[]> {
  return this.http.get<any[]>(`${this.baseUrl}/theme-allocations/${themeId}`);
}

getThemeAllocation(themeId: number): Observable<any[]> {
  return this.http.get<any[]>(`http://localhost:8080/api/theme-allocations/theme/${themeId}`);
}

getHoldingsByPortfolio(portfolioId: number): Observable<any[]> {
  return this.http.get<any[]>(`http://localhost:8080/api/holdings/portfolio/${portfolioId}`);
}


}
