import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicamentousoListarComponent } from './medicamentouso-listar.component';

describe('MedicamentousoListarComponent', () => {
  let component: MedicamentousoListarComponent;
  let fixture: ComponentFixture<MedicamentousoListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MedicamentousoListarComponent]
    });
    fixture = TestBed.createComponent(MedicamentousoListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
