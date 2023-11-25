import { TestBed } from '@angular/core/testing';

import { AtividadeConsultaService } from './atividadeconsulta.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AtividadeConsultaService', () => {
  let service: AtividadeConsultaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [AtividadeConsultaService]
    });
    service = TestBed.inject(AtividadeConsultaService);
  });

  it('should be created', () => {
    const service: AtividadeConsultaService = TestBed.get(AtividadeConsultaService);
    expect(service).toBeTruthy();
   });
});
