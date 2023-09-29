import { TestBed } from '@angular/core/testing';

import { AtividadeLudicaService } from './atividadeludica.service';

describe('ServiceService', () => {
  let service: AtividadeLudicaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AtividadeLudicaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
