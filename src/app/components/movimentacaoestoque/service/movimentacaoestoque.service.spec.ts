import { TestBed } from '@angular/core/testing';

import { MovimentacaoestoqueService } from './movimentacaoestoque.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('MovimentacaoestoqueService', () => {
  let service: MovimentacaoestoqueService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [MovimentacaoestoqueService]
    });
    service = TestBed.inject(MovimentacaoestoqueService);
  });

  it('should be created', () => {
    const service: MovimentacaoestoqueService = TestBed.get(MovimentacaoestoqueService);
    expect(service).toBeTruthy();
   });
});
