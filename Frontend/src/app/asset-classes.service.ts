import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AssetClass } from '../app/models/asset-class.model';

@Injectable({
  providedIn: 'root'
})
export class AssetClassService {
  private apiUrl = 'http://localhost:8080/api/asset-classes';

  constructor(private http: HttpClient) {}

  getAll(): Observable<AssetClass[]> {
    return this.http.get<AssetClass[]>(this.apiUrl);
  }

  getById(id: number): Observable<AssetClass> {
    return this.http.get<AssetClass>(`${this.apiUrl}/${id}`);
  }

  create(asset: AssetClass): Observable<AssetClass> {
    return this.http.post<AssetClass>(this.apiUrl, asset);
  }

  update(id: number, asset: AssetClass): Observable<AssetClass> {
    return this.http.put<AssetClass>(`${this.apiUrl}/${id}`, asset);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
