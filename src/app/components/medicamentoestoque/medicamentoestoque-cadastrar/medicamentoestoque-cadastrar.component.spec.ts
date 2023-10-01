import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicamentoestoqueCadastrarComponent } from './medicamentoestoque-cadastrar.component';

describe('MedicamentoestoqueCadastrarComponent', () => {
  let component: MedicamentoestoqueCadastrarComponent;
  let fixture: ComponentFixture<MedicamentoestoqueCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MedicamentoestoqueCadastrarComponent]
    });
    fixture = TestBed.createComponent(MedicamentoestoqueCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
