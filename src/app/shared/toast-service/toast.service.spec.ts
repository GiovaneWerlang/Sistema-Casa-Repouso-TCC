import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ToastService } from './toast.service';
import { MessageService } from 'primeng/api';

describe('ToastService', () => {
  let service: ToastService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [ToastService, MessageService]
    });
    service = TestBed.inject(ToastService);
  });

  it('should be created', () => {
    const service: ToastService = TestBed.get(ToastService);
    expect(service).toBeTruthy();
   });
});
