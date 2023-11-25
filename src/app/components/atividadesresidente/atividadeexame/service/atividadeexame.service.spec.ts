import { TestBed } from '@angular/core/testing';

import { AtividadeExameService } from './atividadeexame.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AtividadeExameService', () => {
  let service: AtividadeExameService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [AtividadeExameService]
    });
    service = TestBed.inject(AtividadeExameService);
  });

  it('should be created', () => {
    const service: AtividadeExameService = TestBed.get(AtividadeExameService);
    expect(service).toBeTruthy();
   });
});
