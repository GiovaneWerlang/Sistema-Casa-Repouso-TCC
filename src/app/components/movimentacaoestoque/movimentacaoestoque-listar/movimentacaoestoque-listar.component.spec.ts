import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimentacaoestoqueListarComponent } from './movimentacaoestoque-listar.component';

describe('MovimentacaoestoqueListarComponent', () => {
  let component: MovimentacaoestoqueListarComponent;
  let fixture: ComponentFixture<MovimentacaoestoqueListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovimentacaoestoqueListarComponent]
    });
    fixture = TestBed.createComponent(MovimentacaoestoqueListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
