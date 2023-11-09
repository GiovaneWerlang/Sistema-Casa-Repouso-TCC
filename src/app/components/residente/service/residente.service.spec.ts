import { TestBed } from '@angular/core/testing';

import { ResidenteService } from './residente.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ResidenteService', () => {
  let service: ResidenteService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [ResidenteService]
    });
    service = TestBed.inject(ResidenteService);
  });

  it('should be created', () => {
    const service: ResidenteService = TestBed.get(ResidenteService);
    expect(service).toBeTruthy();
   });
});
