import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalCadastrarComponent } from './profissional-cadastrar.component';

describe('ProfissionalCadastrarComponent', () => {
  let component: ProfissionalCadastrarComponent;
  let fixture: ComponentFixture<ProfissionalCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProfissionalCadastrarComponent]
    });
    fixture = TestBed.createComponent(ProfissionalCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
