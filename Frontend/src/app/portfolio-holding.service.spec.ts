import { TestBed } from '@angular/core/testing';

import { PortfolioHoldingService } from './portfolio-holding.service';

describe('PortfolioHoldingService', () => {
  let service: PortfolioHoldingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PortfolioHoldingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
