import { TestBed } from '@angular/core/testing';

import { AtividadeLudicaService } from './atividadeludica.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ServiceService', () => {
  let service: AtividadeLudicaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [AtividadeLudicaService]
    });
    service = TestBed.inject(AtividadeLudicaService);
  });

  it('should be created', () => {
    const service: AtividadeLudicaService = TestBed.get(AtividadeLudicaService);
    expect(service).toBeTruthy();
   });
});
