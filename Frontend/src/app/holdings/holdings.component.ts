import { Component, OnInit } from '@angular/core';
import { HoldingsService } from '../holdings.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-holdings',
  templateUrl: './holdings.component.html',
  styleUrls: ['./holdings.component.css']
})
export class HoldingsComponent implements OnInit {
  

  portfolioId: number | null = null;
  portfolios: any[] = [];
  securities: any[] = [];
  holdings: any[] = [];

  selectedPortfolioId: number | null = null;
  newHolding: any = this.getEmptyHolding();
  editMode = false;

  constructor(private holdingsService: HoldingsService,private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.getPortfolios();
    this.getSecurities();
    this.portfolioId = Number(this.route.snapshot.paramMap.get('portfolioId'));

  }

  getPortfolios() {
    this.holdingsService.getPortfolios().subscribe(data => this.portfolios = data);
  }

  getSecurities() {
    this.holdingsService.getSecurities().subscribe(data => this.securities = data);
  }

  // getHoldings() {
  //   if (!this.selectedPortfolioId) return;
  //   this.holdingsService.getHoldingsByPortfolio(this.selectedPortfolioId)
  //     .subscribe(data => this.holdings = data);
  // }

  getHoldings() {
    if (!this.selectedPortfolioId) return;
  
    this.holdingsService.getHoldingsByPortfolio(this.selectedPortfolioId).subscribe(data => {
      this.holdings = data.map(h => {
        const matchingSecurity = this.securities.find(s => s.id === h.security.id);
        return {
          ...h,
          currentPrice: matchingSecurity?.price || h.price,
          marketValue: (matchingSecurity?.price || h.price) * h.quantity
        };
      });
    });
  }

  saveHolding() {
    if (!this.selectedPortfolioId) return;

    this.newHolding.portfolio = { id: this.selectedPortfolioId };

    if (this.editMode) {
      this.holdingsService.updateHolding(this.newHolding.id, this.newHolding).subscribe(() => {
        this.getHoldings();
        this.resetForm();
      });
    } else {
      this.holdingsService.addHolding(this.newHolding).subscribe(() => {
        this.getHoldings();
        this.resetForm();
      });
    }
  }

  editHolding(holding: any) {
    this.newHolding = JSON.parse(JSON.stringify(holding));
    this.editMode = true;
  }

  deleteHolding(id: number) {
    this.holdingsService.deleteHolding(id).subscribe(() => {
      this.getHoldings();
    });
  }

  resetForm() {
    this.newHolding = this.getEmptyHolding();
    this.editMode = false;
  }

  getEmptyHolding() {
    return {
      portfolio: { id: 0 },
      security: { id: 0 },
      quantity: 0,
      price: 0,
      value: 0,
      equityCategory: ''
    };
  }
}


// import { Component, OnInit } from '@angular/core';
// import { HoldingsService } from '../holdings.service';
// import { ActivatedRoute } from '@angular/router';

// interface Security {
//   id: number;
//   name: string;
// }

// interface Portfolio {
//   id: number;
//   name?: string;
// }

// interface Holding {
//   id?: number;
//   portfolio: { id: number };
//   security: { id: number };
//   quantity: number;
//   price: number;
//   value: number;
//   equityCategory: string;
// }

// @Component({
//   selector: 'app-holdings',
//   templateUrl: './holdings.component.html',
//   styleUrls: ['./holdings.component.css']
// })
// export class HoldingsComponent implements OnInit {

//   // Static list of securities
//   securities: Security[] = [
//     { id: 1, name: 'Tata Consultancy Services' },
//     { id: 2, name: 'Infosys Ltd' },
//     { id: 3, name: 'Reliance Industries' },
//     { id: 4, name: 'HDFC Bank Ltd' },
//     { id: 5, name: 'State Bank of India' },
//     { id: 6, name: 'Apple Inc.' },
//     { id: 7, name: 'Microsoft Corp.' },
//     { id: 8, name: 'JPMorgan Chase & Co.' },
//     { id: 9, name: 'Exxon Mobil Corp.' },
//     { id: 10, name: 'ITC Ltd' },
//     { id: 11, name: 'Maruti Suzuki India Ltd' },
//     { id: 12, name: 'Larsen & Toubro Ltd' },
//     { id: 13, name: 'Bajaj Auto Ltd' },
//     { id: 14, name: 'Coal India Ltd' },
//     { id: 15, name: 'Oil & Natural Gas Corp.' },
//     { id: 16, name: 'Tesla Inc.' },
//     { id: 17, name: 'Coca-Cola Co.' },
//     { id: 18, name: 'Alphabet Inc.' },
//     { id: 19, name: 'Amazon.com Inc.' },
//     { id: 20, name: 'Adani Green Energy Ltd' },
//     { id: 21, name: 'Avenue Supermarts Ltd' },
//     { id: 22, name: 'Pidilite Industries Ltd' },
//     { id: 23, name: 'Asian Paints Ltd' },
//     { id: 24, name: 'Hindustan Unilever Ltd' },
//     { id: 25, name: 'Power Grid Corp. of India' }
//   ];

//   portfolios: Portfolio[] = [];
//   holdings: Holding[] = [];
//   selectedPortfolioId: number | null = null;
//   newHolding: Holding = this.getEmptyHolding();
//   editMode = false;
//   portfolioId: number | null = null;

//   constructor(private holdingsService: HoldingsService, private route: ActivatedRoute) {}

//   ngOnInit(): void {
//     this.portfolioId = Number(this.route.snapshot.paramMap.get('portfolioId')) || null;
//     this.getPortfolios();
//     this.getHoldings(); // Fetch holdings if portfolioId exists
//   }

//   getPortfolios(): void {
//     this.holdingsService.getPortfolios().subscribe({
//       next: data => this.portfolios = data,
//       error: err => console.error('Error fetching portfolios', err)
//     });
//   }

//   getHoldings(): void {
//     if (!this.selectedPortfolioId && !this.portfolioId) return;
//     const portfolioToFetch = this.selectedPortfolioId || this.portfolioId!;
//     this.holdingsService.getHoldingsByPortfolio(portfolioToFetch).subscribe({
//       next: data => this.holdings = data,
//       error: err => console.error('Error fetching holdings', err)
//     });
//   }

//   saveHolding(): void {
//     if (!this.selectedPortfolioId) return;
//     this.newHolding.portfolio.id = this.selectedPortfolioId;

//     if (this.editMode && this.newHolding.id) {
//       this.holdingsService.updateHolding(this.newHolding.id, this.newHolding).subscribe({
//         next: () => {
//           this.getHoldings();
//           this.resetForm();
//         },
//         error: err => console.error('Error updating holding', err)
//       });
//     } else {
//       this.holdingsService.addHolding(this.newHolding).subscribe({
//         next: () => {
//           this.getHoldings();
//           this.resetForm();
//         },
//         error: err => console.error('Error adding holding', err)
//       });
//     }
//   }

//   editHolding(holding: Holding): void {
//     this.newHolding = { ...holding }; // Deep copy
//     this.editMode = true;
//   }

// deleteHolding(id?: number) {
//   if (id == null) return;
//   this.holdingsService.deleteHolding(id).subscribe(() => {
//     this.getHoldings();
//   });
// }

//   resetForm(): void {
//     this.newHolding = this.getEmptyHolding();
//     this.editMode = false;
//   }

//   getEmptyHolding(): Holding {
//     return {
//       portfolio: { id: 0 },
//       security: { id: 0, name: '' },
//       quantity: 0,
//       price: 0,
//       value: 0,
//       equityCategory: ''
//     };
//   }
// }
