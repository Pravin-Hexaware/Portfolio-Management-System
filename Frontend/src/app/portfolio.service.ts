import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Theme {
  id: number;
  name: string;
}

export interface Portfolio {
  id: number;
  name: string;
  type: string;
  currency: string;
  benchmark: string;
  exchange: string;
  initialInvestment: number;
  currentValue: number;
  rebalancingFrequency: string;
  status: string;
  theme: { id: number; name?: string };
}

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {
  private baseUrl = 'http://localhost:8080/api/portfolios';
  private themeUrl = 'http://localhost:8080/api/investment-themes';
  private dropdownUrl='http://localhost:8080/api/dropdown';


  constructor(private http: HttpClient) {}

  getPortfolios(): Observable<Portfolio[]> {
    return this.http.get<Portfolio[]>(this.baseUrl);
  }

  getPortfolioById(id: number): Observable<Portfolio> {
    return this.http.get<Portfolio>(`${this.baseUrl}/${id}`);
  }

  createPortfolio(portfolio: Portfolio): Observable<Portfolio> {
    return this.http.post<Portfolio>(this.baseUrl, portfolio);
  }

  updatePortfolio(id: number, portfolio: Portfolio): Observable<Portfolio> {
    return this.http.put<Portfolio>(`${this.baseUrl}/${id}`, portfolio);
  }

  deletePortfolio(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  getThemes(): Observable<Theme[]> {
    return this.http.get<Theme[]>(this.themeUrl);
  }
  getBenchmarks(): Observable<any[]> {
    return this.http.get<any[]>(`${this.dropdownUrl}/benchmarks`);
  }

  getTypes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.dropdownUrl}/types`);
  }

  getRebalances(): Observable<any[]> {
    return this.http.get<any[]>(`${this.dropdownUrl}/rebalances`);
  }


}
