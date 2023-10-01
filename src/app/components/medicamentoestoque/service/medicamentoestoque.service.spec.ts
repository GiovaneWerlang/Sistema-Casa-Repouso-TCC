import { TestBed } from '@angular/core/testing';

import { MedicamentoestoqueService } from './medicamentoestoque.service';

describe('MedicamentoestoqueService', () => {
  let service: MedicamentoestoqueService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicamentoestoqueService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
