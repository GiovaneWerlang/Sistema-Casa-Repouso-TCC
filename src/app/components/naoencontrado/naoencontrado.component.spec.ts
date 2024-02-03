import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NaoencontradoComponent } from './naoencontrado.component';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';

describe('NaoencontradoComponent', () => {
  let component: NaoencontradoComponent;
  let fixture: ComponentFixture<NaoencontradoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NaoencontradoComponent],
      imports: [
        CardModule,
        ButtonModule,
      ],
    });
    fixture = TestBed.createComponent(NaoencontradoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
