import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaCadastrarComponent } from './consulta-cadastrar.component';

describe('ConsultaCadastrarComponent', () => {
  let component: ConsultaCadastrarComponent;
  let fixture: ComponentFixture<ConsultaCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConsultaCadastrarComponent]
    });
    fixture = TestBed.createComponent(ConsultaCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
