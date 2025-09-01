import { Component, OnInit } from '@angular/core';
import { SecurityService } from '../security.service';
import { Security } from '../models/Security.model';
import { AssetClass } from '../models/asset-class.model';
 
@Component({
  selector: 'app-security-list',
  templateUrl: './security-list.component.html',
  styleUrls: ['./security-list.component.css']
})
export class SecurityListComponent implements OnInit {
  // Columns for display
  displayedColumns: string[] = [
    'exchange', 'symbol', 'name', 'isin', 'sector', 'industry',
    'currency', 'country', 'securityCode', 'series', 'price', 'assetClassId', 'actions'
  ];
 
  // Full list of securities from backend
  securities: Security[] = [];
 
  // Filtered list for display
  dataSource: Security[] = [];
 
  // Asset class data
  assetClasses: AssetClass[] = [];
  combinedAssetOptions: { label: string; id: number }[] = [];
  exchanges: string[] = [];
  sectors: string[] = [];
  industries: string[] = [];
  currencies: { code: string; name: string }[] = [];
  countries: string[] = [];
 
  // Form model for new security
  newSecurity: Security = {
    exchange: '',
    symbol: '',
    name: '',
    isin: '',
    sector: '',
    industry: '',
    currency: '',
    country: '',
    securityCode: '',
    series: '',
    price: null,
    assetClass: {
      id: null
    }
  };
 
  // Toggle for add form
  showAddForm = false;
 
  constructor(private securityService: SecurityService) {}
 
  ngOnInit(): void {
    this.loadSecurities();
    this.loadAssetClasses();
    this.loadDropdownData();
 
  }
 
  // Load all securities from backend
  loadSecurities(): void {
    this.securityService.getAllSecurities().subscribe(data => {
      this.securities = data;
      this.dataSource = data;
    });
  }
 
  // Load asset classes and build combined dropdown options
  loadAssetClasses(): void {
    this.securityService.getAllAssetClasses().subscribe(data => {
      this.assetClasses = data;
      localStorage.setItem('assetClasses', JSON.stringify(data));
      error: () => {
        const cached = localStorage.getItem('assetClasses');
        if (cached) {
          this.assetClasses = JSON.parse(cached);
        }
      }    
      this.combinedAssetOptions = data
        .filter(a => a.id !== null)
        .map(a => ({
          label: `${a.className} – ${a.subClassName}`,
          id: a.id!
        }));
    });
  }
 
  getAssetClassLabelById(id: number | null): string {
    if (id === null) return 'Not Assigned';
    const match = this.assetClasses.find(a => a.id === id);
    return match ? `${match.className} – ${match.subClassName}` : 'Unknown';
  }
 
  // Filter securities by name, symbol, or ISIN
  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value.trim().toLowerCase();
    this.dataSource = this.securities.filter(security =>
      security.name.toLowerCase().includes(filterValue) ||
      security.symbol.toLowerCase().includes(filterValue) ||
      security.isin.toLowerCase().includes(filterValue)
    );
  }
 
  // Show the add form
  toggleAddForm(): void {
    this.showAddForm = true;
  }
 
  // Reset form and hide it
  cancelAdd(): void {
    this.newSecurity = {
      exchange: '',
      symbol: '',
      name: '',
      isin: '',
      sector: '',
      industry: '',
      currency: '',
      country: '',
      securityCode: '',
      series: '',
      price: null,
      assetClass: {
        id: null
      }
    };
    this.showAddForm = false;
  }
 
  // Create a new security
  createSecurity(): void {
    this.securityService.createSecurity(this.newSecurity).subscribe(() => {
      this.cancelAdd();
      this.loadSecurities();
    });
  }
 
  // Delete a security by ID
  deleteSecurity(id: number): void {
    this.securityService.deleteSecurity(id).subscribe(() => {
      this.loadSecurities();
    });
  }
 
  // Placeholder for edit functionality
  editSecurity(security: Security): void {
    alert(`Edit feature not implemented yet for ${security.name}`);
  }
 
  loadDropdownData(): void {
    this.securityService.getAllExchanges().subscribe(data => {
      this.exchanges = data.map(e => e.name);
    });
 
    this.securityService.getAllSectors().subscribe(data => {
      this.sectors = data.map(s => s.name);
    });
 
    this.securityService.getAllIndustries().subscribe(data => {
      this.industries = data.map(i => i.name);
    });
 
    this.securityService.getAllCurrencies().subscribe(data => {
      this.currencies = data.map(c => ({ code: c.code, name: c.name }));
    });
 
    this.securityService.getAllCountries().subscribe(data => {
      this.countries = data.map(c => c.name);
    });
  }
 
}
 