import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ConfiguracaoSistemaService } from './configuracaosistema.service';

describe('ConfiguracaoSistemaService', () => {
  let service: ConfiguracaoSistemaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [ConfiguracaoSistemaService]
    });
    service = TestBed.inject(ConfiguracaoSistemaService);
  });

  it('should be created', () => {
    const service: ConfiguracaoSistemaService = TestBed.get(ConfiguracaoSistemaService);
    expect(service).toBeTruthy();
   });
});
