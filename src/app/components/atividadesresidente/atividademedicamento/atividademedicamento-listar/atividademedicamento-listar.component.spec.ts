import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividademedicamentoListarComponent } from './atividademedicamento-listar.component';

describe('AtividademedicamentoListarComponent', () => {
  let component: AtividademedicamentoListarComponent;
  let fixture: ComponentFixture<AtividademedicamentoListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividademedicamentoListarComponent]
    });
    fixture = TestBed.createComponent(AtividademedicamentoListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
