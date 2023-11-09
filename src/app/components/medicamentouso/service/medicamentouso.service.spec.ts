import { TestBed } from '@angular/core/testing';

import { MedicamentousoService } from './medicamentouso.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('MedicamentousoService', () => {
  let service: MedicamentousoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [MedicamentousoService]
    });
    service = TestBed.inject(MedicamentousoService);
  });

  it('should be created', () => {
    const service: MedicamentousoService = TestBed.get(MedicamentousoService);
    expect(service).toBeTruthy();
   });
});
