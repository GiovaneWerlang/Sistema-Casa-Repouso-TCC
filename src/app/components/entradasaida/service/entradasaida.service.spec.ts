import { TestBed } from '@angular/core/testing';

import { EntradasaidaService } from './entradasaida.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('EntradasaidaService', () => {
  let service: EntradasaidaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [EntradasaidaService]
    });
    service = TestBed.inject(EntradasaidaService);
  });

  it('should be created', () => {
    const service: EntradasaidaService = TestBed.get(EntradasaidaService);
    expect(service).toBeTruthy();
   });
});
