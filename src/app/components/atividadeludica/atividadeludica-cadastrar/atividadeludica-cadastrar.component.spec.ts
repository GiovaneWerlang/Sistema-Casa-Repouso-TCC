import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeludicaCadastrarComponent } from './atividadeludica-cadastrar.component';

describe('AtividadeludicaCadastrarComponent', () => {
  let component: AtividadeludicaCadastrarComponent;
  let fixture: ComponentFixture<AtividadeludicaCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividadeludicaCadastrarComponent]
    });
    fixture = TestBed.createComponent(AtividadeludicaCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
