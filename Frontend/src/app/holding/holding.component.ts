import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { forkJoin } from 'rxjs';
import { HoldingService } from '../holding.service';
import { PortfolioHolding } from '../models/portfolio-holding.model';
import { Portfolio } from '../models/portfolio.model';

@Component({
  selector: 'app-holding',
  templateUrl: './holding.component.html'
})
export class HoldingComponent implements OnInit {
  portfolioId!: number;
  portfolio!: Portfolio;
  holdings: PortfolioHolding[] = [];
  

  // Inline editing
  editingHoldingId: number | null = null;
  holdingEdits: Partial<PortfolioHolding> = {};

  // Securities for adding new holdings
  securities: any[] = [];
  pagedSecurities: any[] = [];
  pageSize = 30;
  totalItems = 0;

  constructor(
    private route: ActivatedRoute,
    private holdingService: HoldingService
  ) {}

  ngOnInit(): void {
    this.portfolioId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadPortfolioDetails();
    // this.loadHoldings();
    // this.loadSecurities();
    this.loadSecurities(() => {
      this.loadHoldings();
    });
  
  }

  loadPortfolioDetails(): void {
    this.holdingService.getPortfolio(this.portfolioId).subscribe(data => {
      this.portfolio = data;
    });
  }

  // loadHoldings(): void {
  //   this.holdingService.getHoldingsByPortfolioId(this.portfolioId).subscribe(data => {
  //     this.holdings = data;
  //   });
  // }

  loadHoldings(): void {
    this.holdingService.getHoldingsByPortfolioId(this.portfolioId).subscribe(data => {
      this.holdings = data.map(h => {
        const matchingSecurity = this.securities.find(s => s.id === h.security.id);
        const currentPrice = matchingSecurity?.price || h.price;
        return {
          ...h,
          currentPrice,
          marketValue: currentPrice * h.quantity
        };
      });
    });
  }
  
  // loadSecurities(): void {
  //   this.holdingService.getSecurities().subscribe(data => {
  //     this.securities = data.map(sec => ({
  //       ...sec,
  //       selected: false,
  //       quantity: null
  //     }));
  //     this.totalItems = this.securities.length;
  //     this.updatePagedSecurities(0);
  //   });
  // }

  loadSecurities(callback?: () => void): void {
    this.holdingService.getSecurities().subscribe(data => {
      this.securities = data.map(sec => ({
        ...sec,
        selected: false,
        quantity: null
      }));
      this.totalItems = this.securities.length;
      this.updatePagedSecurities(0);
      if (callback) callback();
    });
  }

  updatePagedSecurities(pageIndex: number, source: any[] = this.securities): void {
    const start = pageIndex * this.pageSize;
    const end = start + this.pageSize;
    this.pagedSecurities = source.slice(start, end);
  }

  applyFilter(event: any): void {
    const query = event.target.value.toLowerCase();
    const filtered = this.securities.filter(sec =>
      sec.name.toLowerCase().includes(query) ||
      sec.symbol.toLowerCase().includes(query) ||
      sec.isin.toLowerCase().includes(query)
    );
    this.totalItems = filtered.length;
    this.updatePagedSecurities(0, filtered);
  }

  toggleSelection(security: any): void {
    security.selected = !security.selected;
    if (!security.selected) {
      security.quantity = null;
    }
  }

  cancelEdit(): void {
    this.editingHoldingId = null;
    this.holdingEdits = {};
  }

  deleteHolding(id: number): void {
    // Step 1: Get holding details
    const holding = this.holdings.find(h => h.id === id);
    if (!holding) {
      alert('Holding not found.');
      return;
    }
    const securityId = holding.security.id;
    const quantity = holding.quantity;
    // Step 2: Get current price from securities table
    this.holdingService.getSecurityById(securityId).subscribe({
      next: security => {
        const currentPrice = security.price;
        const saleValue = currentPrice * quantity;
        // Step 3: Update portfolio balance
        const updatedPortfolio = {
          ...this.portfolio,
          currentValue: this.portfolio.currentValue + saleValue
        };
        this.holdingService.updatePortfolio(updatedPortfolio).subscribe({
          next: () => {
            // Step 4: Delete holding from DB
            this.holdingService.deleteHolding(id).subscribe({
              next: () => {
                this.holdings = this.holdings.filter(h => h.id !== id);
                console.log(`Holding ${id} sold at ₹${currentPrice} and deleted successfully`);
                this.loadHoldings();
                this.loadPortfolioDetails();
              },
              error: err => {
                console.error(`Failed to delete holding ${id}`, err);
                alert('Failed to delete holding. Please try again.');
              }
            });
          },
          error: err => {
            console.error('Failed to update portfolio after sale', err);
            alert('Portfolio update failed. Cannot proceed with sale.');
          }
        });
      },
      error: err => {
        console.error(`Failed to fetch security ${securityId}`, err);
        alert('Failed to fetch current price. Cannot proceed with sale.');
      }
    });
  }

  


  addToPortfolio(stock: any): void {
    stock.selected = true;
    stock.quantity = 1;
  }

  removeFromPortfolio(stock: any): void {
    stock.selected = false;
    stock.quantity = null;
  }

  increaseQuantity(stock: any): void {
    stock.quantity++;
    stock.selected = true;
  }
  
  decreaseQuantity(stock: any): void {
    if (stock.quantity > 1) {
      stock.quantity--;
    } else {
      this.removeFromPortfolio(stock);
    }
  }

  submitHolding(): void {
    const selectedHolding = this.securities
      .filter(sec => sec.selected && sec.quantity > 0)
      .map(sec => ({
        quantity: sec.quantity,
        price: sec.price,
        equityCategory: sec.assetClass.subClassName,
        security: { id: sec.id },
        portfolio: { id: this.portfolioId },
        assetClassId: sec.assetClass.id,
        value: sec.price * sec.quantity
      }));
  
    if (selectedHolding.length === 0) {
      alert('Please add at least one security with quantity.');
      return;
    }
  
    this.holdingService.getHoldingsByPortfolio(this.portfolioId).subscribe({
      next: (existingHoldings: any[]) => {
        const existingSpend: { [key: number]: number } = {};
  
        existingHoldings.forEach(h => {
          const assetClassId = h.security.assetClass?.id;
          const value = h.price * h.quantity;
          if (assetClassId) {
            existingSpend[assetClassId] = (existingSpend[assetClassId] || 0) + value;
          }
        });
  
        selectedHolding.forEach(h => {
          existingSpend[h.assetClassId] = (existingSpend[h.assetClassId] || 0) + h.value;
        });
  
        const themeId = this.portfolio.theme?.id;
        this.holdingService.getThemeAllocation(themeId).subscribe({
          next: (allocations: any[]) => {
            const initialInvestment = this.portfolio.initialInvestment;
            let allocationExceeded = false;
            const messages: string[] = [];
  
            allocations.forEach(a => {
              const allowed = initialInvestment * (a.allocationPercent / 100);
              const actual = existingSpend[a.assetClass.id] || 0;
  
              if (actual > allowed) {
                allocationExceeded = true;
                messages.push(`${a.assetClass.className}: ₹${actual.toFixed(2)} / ₹${allowed.toFixed(2)}`);
              }
            });
  
            if (allocationExceeded) {
              alert('Allocation limits exceeded:\n' + messages.join('\n'));
              return;
            }
  
            const totalTransactionValue = selectedHolding.reduce((sum, h) => sum + h.value, 0);
            const updatedPortfolio = {
              ...this.portfolio,
              currentValue: this.portfolio.currentValue + totalTransactionValue
            };
  
            this.holdingService.updatePortfolio(updatedPortfolio).subscribe({
              next: () => {
                const requests = selectedHolding.map(h => {
                  const payload = {
                    quantity: h.quantity,
                    price: h.price,
                    equityCategory: h.equityCategory,
                    security: { id: h.security.id },
                    portfolio: { id: this.portfolioId }
                  };
                  return this.holdingService.submitHolding(payload);
                });
  
                forkJoin(requests).subscribe({
                  next: () => {
                    alert('All holdings submitted successfully.');
                    this.loadHoldings();
                    this.loadPortfolioDetails();
                  },
                  error: (err: any) => {
                    console.error('One or more submissions failed:', err);
                    alert('Some holdings failed to submit. Please check the console.');
                  }
                });
              },
              error: (err: any) => {
                console.error('Failed to update portfolio:', err);
                alert('Portfolio update failed. Cannot proceed with holdings.');
              }
            });
          },
          error: (err: any) => {
            console.error('Failed to fetch theme allocation:', err);
            alert('Cannot validate allocation. Submission aborted.');
          }
        });
      },
      error: (err: any) => {
        console.error('Failed to fetch existing holdings:', err);
        alert('Cannot validate holdings. Submission aborted.');
      }
    });
  }

  themeAllocations: { assetClassId: number; allocationPercentage: number }[] = [];

loadThemeAllocations(): void {
  if (this.portfolio?.theme?.id) {
    this.holdingService.getThemeAllocations(this.portfolio.theme.id).subscribe({
      next: data => this.themeAllocations = data,
      error: err => console.error('Failed to load theme allocations', err)
    });
  }
}


editHolding(holding: PortfolioHolding): void {
  this.editingHoldingId = holding.id || null;
  this.holdingEdits = { ...holding };
}

getTotalMarketValue(): number {
  return this.holdings.reduce((sum, h) => sum + (h.marketValue || 0), 0);
}


sortHoldings(field: 'currentPrice' | 'marketValue'): void {
  this.holdings.sort((a, b) => (b[field] || 0) - (a[field] || 0));
}

getGainClass(holding: PortfolioHolding): string {
  if (holding.marketValue == null || holding.value == null) return 'text-muted';
  if (holding.marketValue > holding.value) return 'text-success';
  if (holding.marketValue < holding.value) return 'text-danger';
  return 'text-muted';
}
  
}
