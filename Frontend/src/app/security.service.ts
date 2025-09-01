import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Security } from '../app/models/Security.model';
import { AssetClass } from '../app/models/asset-class.model';
 
 
@Injectable({
  providedIn: 'root'
})
export class SecurityService {
  private baseUrl = 'http://localhost:8080/api';
 
  private assetClassUrl = 'http://localhost:8080/api/asset-classes/dto';
 
  constructor(private http: HttpClient) {}
 
    // 🔐 Securities
    getAllSecurities(): Observable<Security[]> {
      return this.http.get<Security[]>(`${this.baseUrl}/securities`);
    }
 
    createSecurity(security: Security): Observable<Security> {
      return this.http.post<Security>(`${this.baseUrl}/securities`, security);
    }
 
    deleteSecurity(id: number): Observable<void> {
      return this.http.delete<void>(`${this.baseUrl}/securities/${id}`);
    }
 
 
  getSecurityById(id: number): Observable<Security> {
    return this.http.get<Security>(`${this.baseUrl}/${id}`);
  }
 
  getSecurityByIsin(isin: string): Observable<Security> {
      return this.http.get<Security>(`${this.baseUrl}/isin/${isin}`);
    }
 
   
    getSecurityBySymbol(symbol: string): Observable<Security> {
      return this.http.get<Security>(`${this.baseUrl}/symbol/${symbol}`);
    }
 
   
    getSecuritiesBySector(sector: string): Observable<Security[]> {
      return this.http.get<Security[]>(`${this.baseUrl}/sector/${sector}`);
    }
 
   
    updateSecurity(id: number, security: Security): Observable<Security> {
      return this.http.put<Security>(`${this.baseUrl}/${id}`, security);
    }
 
    getAllAssetClasses(): Observable<AssetClass[]> {
      return this.http.get<AssetClass[]>(this.assetClassUrl);
    }
 
    getAllExchanges(): Observable<{ id: number; name: string }[]> {
      return this.http.get<{ id: number; name: string }[]>(`${this.baseUrl}/exchanges`);
    }
 
    // 🧭 Sectors
    getAllSectors(): Observable<{ id: number; name: string }[]> {
      return this.http.get<{ id: number; name: string }[]>(`${this.baseUrl}/sectors`);
    }
 
    // 🏭 Industries
    getAllIndustries(): Observable<{ id: number; name: string }[]> {
      return this.http.get<{ id: number; name: string }[]>(`${this.baseUrl}/industries`);
    }
 
    // 💱 Currencies
    getAllCurrencies(): Observable<{ id: number; code: string; name: string }[]> {
      return this.http.get<{ id: number; code: string; name: string }[]>(`${this.baseUrl}/currencies`);
    }
 
    // 🌍 Countries
    getAllCountries(): Observable<{ id: number; name: string }[]> {
      return this.http.get<{ id: number; name: string }[]>(`${this.baseUrl}/countries`);
    }
 
 
}
 
 