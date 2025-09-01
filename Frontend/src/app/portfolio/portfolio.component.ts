import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { PortfolioService } from '../portfolio.service';



@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html'
})
export class PortfolioComponent implements OnInit {
  portfolios: any[] = [];
  assetClasses: string[] = [];
  themes:any[]=[];
  showForm = false;
  editMode = false;
  selectedPortfolio: any = this.getEmptyPortfolio();
  currencies: string[] = [];
exchanges: string[] = [];
benchmarks: string[] = [];
types: string[] = [];
rebalances: string[] = [];


  constructor(private http: HttpClient,private router: Router,private portfolioService: PortfolioService) {}

  ngOnInit(): void {
    this.loadPortfolios();
    this.loadDropdownValues();
    this.loadThemes();
    this.loadAssetClasses();
    this.loadBenchmarks();
    this.loadTypes();
    this.loadRebalances();




  }

  getEmptyPortfolio() {
    return {
      id: null,
      name: '',
      type: '',
      currency: '',
      benchmark: '',
      exchange: '',
      initialInvestment: 0,
      currentValue: 0,
      rebalancingFrequency: '',
      theme: { 
        id: null, 
        name: '', 
        description: '', 
        riskLevel: '', 
        investmentHorizon: '' 
      },
      status: ''
    };
  }

  loadPortfolios() {
    this.http.get<any[]>('http://localhost:8080/api/portfolios')
      .subscribe(data => this.portfolios = data);
  }

  toggleForm() {
    this.showForm = !this.showForm;
    if (!this.showForm) {
      this.editMode = false;
      this.selectedPortfolio = this.getEmptyPortfolio();
    }
  }

  savePortfolio() {
    const nameExists = this.portfolios.some(p => 
      p.name.trim().toLowerCase() === this.selectedPortfolio.name.trim().toLowerCase() &&
      (!this.editMode || p.id !== this.selectedPortfolio.id)
    );
  
    if (nameExists) {
      alert('A portfolio with this name already exists. Please choose a different name.');
      return;
    }
  
    if (this.editMode) {
      this.http.put(`http://localhost:8080/api/portfolios/${this.selectedPortfolio.id}`, this.selectedPortfolio)
        .subscribe(() => {
          this.loadPortfolios();
          this.cancelEdit();
        });
    } else {
      this.http.post('http://localhost:8080/api/portfolios', this.selectedPortfolio)
        .subscribe(() => {
          this.loadPortfolios();
          this.toggleForm();
        });
    }
  }

  editPortfolio(portfolio: any) {
    this.selectedPortfolio = { 
      ...portfolio, 
      theme: portfolio.theme || { 
        id: null, 
        name: '', 
        description: '', 
        riskLevel: '', 
        investmentHorizon: '' 
      }
    };
    this.editMode = true;
    this.showForm = true;
  }

  cancelEdit() {
    this.editMode = false;
    this.showForm = false;
    this.selectedPortfolio = this.getEmptyPortfolio();
  }

  deletePortfolio(id: number) {
    if (confirm('Are you sure you want to delete this portfolio?')) {
      this.http.delete(`http://localhost:8080/api/portfolios/${id}`)
        .subscribe(() => this.loadPortfolios());
    }
  }

  viewHoldings(portfolioId: number): void {
    const selected = this.portfolios.find(p => p.id === portfolioId);
    if (selected?.status === 'Inactive') {
      alert(`Portfolio "${selected.name}" is inactive and cannot be viewed.`);
      return;
    }
  
    // Navigate only if active
    this.router.navigate(['/holding', portfolioId]);
  }

onInitialInvestmentChange(): void {
  if (!this.editMode) {
    this.selectedPortfolio.currentValue = 0;
  }
}

loadDropdownValues(): void {
  this.http.get<string[]>('http://localhost:8080/api/securities/currencies')
    .subscribe(data => this.currencies = data);

  this.http.get<string[]>('http://localhost:8080/api/securities/exchanges')
    .subscribe(data => this.exchanges = data);
}

loadThemes(): void {
  this.http.get<any[]>('http://localhost:8080/api/theme-allocations')
    .subscribe(data => {
      const themeMap = new Map();
      data.forEach(item => {
        const theme = item.theme;
        if (!themeMap.has(theme.id)) {
          themeMap.set(theme.id, theme);
        }
      });
      this.themes = Array.from(themeMap.values());
    });
}


loadAssetClasses(): void {
  this.http.get<any[]>('http://localhost:8080/api/asset-classes')
    .subscribe(data => {
      const uniqueClasses = new Set<string>();
      data.forEach(item => {
        if (item.className) {
          uniqueClasses.add(item.className);
        }
      });
      this.assetClasses = Array.from(uniqueClasses);
    });
}

formatAmount(value: number): string {
  return value != null ? value.toFixed(2) : '0.00';
}

loadBenchmarks(): void {
  this.portfolioService.getBenchmarks().subscribe(data => {
    this.benchmarks = data.map(item => item.name);
  });
}

loadTypes(): void {
  this.portfolioService.getTypes().subscribe(data => {
    this.types = data.map(item => item.name);
  });
}

loadRebalances(): void {
  this.portfolioService.getRebalances().subscribe(data => {
    this.rebalances = data.map(item => item.frequency);
  });
}



}



