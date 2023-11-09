import { TestBed } from '@angular/core/testing';

import { ExameService } from './exame.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ExameService', () => {
  let service: ExameService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [ExameService]
    });
    service = TestBed.inject(ExameService);
  });

  it('should be created', () => {
    const service: ExameService = TestBed.get(ExameService);
    expect(service).toBeTruthy();
   });
});
