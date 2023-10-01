import { TestBed } from '@angular/core/testing';

import { MovimentacaoestoqueService } from './movimentacaoestoque.service';

describe('MovimentacaoestoqueService', () => {
  let service: MovimentacaoestoqueService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MovimentacaoestoqueService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
