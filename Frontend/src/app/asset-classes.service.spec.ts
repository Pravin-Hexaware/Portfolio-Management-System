import { TestBed } from '@angular/core/testing';

import { AssetClassesService } from './asset-classes.service';

describe('AssetClassesService', () => {
  let service: AssetClassesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssetClassesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
