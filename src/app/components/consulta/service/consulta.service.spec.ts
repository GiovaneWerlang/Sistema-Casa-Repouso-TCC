import { TestBed } from '@angular/core/testing';

import { ConsultaService } from './consulta.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ConsultaService', () => {
  let service: ConsultaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [ConsultaService]
    });
    service = TestBed.inject(ConsultaService);
  });

  it('should be created', () => {
    const service: ConsultaService = TestBed.get(ConsultaService);
    expect(service).toBeTruthy();
   });
});
