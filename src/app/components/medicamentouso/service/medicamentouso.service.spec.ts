import { TestBed } from '@angular/core/testing';

import { MedicamentousoService } from './medicamentouso.service';

describe('MedicamentousoService', () => {
  let service: MedicamentousoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicamentousoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
