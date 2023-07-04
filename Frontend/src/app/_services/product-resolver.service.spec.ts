import { TestBed } from '@angular/core/testing';

import { ProductResolverServiceService } from './product-resolver.service';

describe('ProductResolverServiceService', () => {
  let service: ProductResolverServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductResolverServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
