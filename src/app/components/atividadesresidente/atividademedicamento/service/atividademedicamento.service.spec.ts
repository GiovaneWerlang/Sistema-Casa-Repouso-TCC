import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { AtividadeMedicamentoService } from './atividademedicamento.service';

describe('AtividadeMedicamentoService', () => {
  let service: AtividadeMedicamentoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [AtividadeMedicamentoService]
    });
    service = TestBed.inject(AtividadeMedicamentoService);
  });

  it('should be created', () => {
    const service: AtividadeMedicamentoService = TestBed.get(AtividadeMedicamentoService);
    expect(service).toBeTruthy();
   });
});
