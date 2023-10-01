import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimentacaoestoqueCadastrarComponent } from './movimentacaoestoque-cadastrar.component';

describe('MovimentacaoestoqueCadastrarComponent', () => {
  let component: MovimentacaoestoqueCadastrarComponent;
  let fixture: ComponentFixture<MovimentacaoestoqueCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovimentacaoestoqueCadastrarComponent]
    });
    fixture = TestBed.createComponent(MovimentacaoestoqueCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
