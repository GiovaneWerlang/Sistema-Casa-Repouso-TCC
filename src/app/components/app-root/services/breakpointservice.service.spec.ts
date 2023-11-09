import { TestBed } from '@angular/core/testing';

import { BreakpointserviceService } from './breakpointservice.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('BreakpointserviceService', () => {
  let service: BreakpointserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [BreakpointserviceService]
    });
    service = TestBed.inject(BreakpointserviceService);
  });

  it('should be created', () => {
    const service: BreakpointserviceService = TestBed.get(BreakpointserviceService);
    expect(service).toBeTruthy();
   });
});
