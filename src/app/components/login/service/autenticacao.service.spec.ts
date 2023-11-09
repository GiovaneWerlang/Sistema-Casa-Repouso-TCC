import { TestBed } from '@angular/core/testing';

import { AutenticacaoService } from './autenticacao.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AutenticacaoService', () => {
  let service: AutenticacaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [AutenticacaoService]
    });
    service = TestBed.inject(AutenticacaoService);
  });

  it('should be created', () => {
    const service: AutenticacaoService = TestBed.get(AutenticacaoService);
    expect(service).toBeTruthy();
   });
});
