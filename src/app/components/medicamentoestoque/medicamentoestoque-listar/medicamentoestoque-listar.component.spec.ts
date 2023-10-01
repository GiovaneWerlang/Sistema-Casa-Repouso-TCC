import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicamentoestoqueListarComponent } from './medicamentoestoque-listar.component';

describe('MedicamentoestoqueListarComponent', () => {
  let component: MedicamentoestoqueListarComponent;
  let fixture: ComponentFixture<MedicamentoestoqueListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MedicamentoestoqueListarComponent]
    });
    fixture = TestBed.createComponent(MedicamentoestoqueListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
