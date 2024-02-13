import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MensagemSubscriptionService } from './mensagemsubscription.service';

describe('MensagemSubscriptionService', () => {
  let service: MensagemSubscriptionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [MensagemSubscriptionService]
    });
    service = TestBed.inject(MensagemSubscriptionService);
  });

  it('should be created', () => {
    const service: MensagemSubscriptionService = TestBed.get(MensagemSubscriptionService);
    expect(service).toBeTruthy();
   });
});
