import { TestBed } from '@angular/core/testing';

import { EntradasaidaService } from './entradasaida.service';

describe('EntradasaidaService', () => {
  let service: EntradasaidaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EntradasaidaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
