import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividademedicamentoEditarComponent } from './atividademedicamento-editar.component';

describe('AtividademedicamentoEditarComponent', () => {
  let component: AtividademedicamentoEditarComponent;
  let fixture: ComponentFixture<AtividademedicamentoEditarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividademedicamentoEditarComponent]
    });
    fixture = TestBed.createComponent(AtividademedicamentoEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
