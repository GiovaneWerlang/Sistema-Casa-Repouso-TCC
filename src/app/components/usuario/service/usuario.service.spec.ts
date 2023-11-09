import { TestBed } from '@angular/core/testing';

import { UsuarioService } from './usuario.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('UsuarioService', () => {
  let service: UsuarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [UsuarioService]
    });
    service = TestBed.inject(UsuarioService);
  });

  it('should be created', () => {
    const service: UsuarioService = TestBed.get(UsuarioService);
    expect(service).toBeTruthy();
   });
});
