import { TestBed } from '@angular/core/testing';

import { MedicamentoestoqueService } from './medicamentoestoque.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('MedicamentoestoqueService', () => {
  let service: MedicamentoestoqueService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [MedicamentoestoqueService]
    });
    service = TestBed.inject(MedicamentoestoqueService);
  });

  it('should be created', () => {
    const service: MedicamentoestoqueService = TestBed.get(MedicamentoestoqueService);
    expect(service).toBeTruthy();
   });
});
