import { TestBed } from '@angular/core/testing';

import { ProfissionalService } from './profissional.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ProfissionalService', () => {
  let service: ProfissionalService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [ProfissionalService]
    });
    service = TestBed.inject(ProfissionalService);
  });

  it('should be created', () => {
    const service: ProfissionalService = TestBed.get(ProfissionalService);
    expect(service).toBeTruthy();
   });
});
