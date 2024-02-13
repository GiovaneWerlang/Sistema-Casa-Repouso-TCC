import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NotificacoesService } from './notificacoes.service';

describe('NotificacoesService', () => {
  let service: NotificacoesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [NotificacoesService]
    });
    service = TestBed.inject(NotificacoesService);
  });

  it('should be created', () => {
    const service: NotificacoesService = TestBed.get(NotificacoesService);
    expect(service).toBeTruthy();
   });
});
