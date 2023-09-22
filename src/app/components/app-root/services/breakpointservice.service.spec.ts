import { TestBed } from '@angular/core/testing';

import { BreakpointserviceService } from './breakpointservice.service';

describe('BreakpointserviceService', () => {
  let service: BreakpointserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BreakpointserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
