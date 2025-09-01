import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HoldingsService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // Portfolios
  getPortfolios(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/portfolios`);
  }

  // Securities
  getSecurities(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/securities`);
  }

  // Holdings
  getHoldingsByPortfolio(portfolioId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/holdings/portfolio/${portfolioId}`);
  }

  addHolding(holding: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/holdings`, holding);
  }

  updateHolding(id: number, holding: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/holdings/${id}`, holding);
  }

  deleteHolding(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/holdings/${id}`);
  }
}
