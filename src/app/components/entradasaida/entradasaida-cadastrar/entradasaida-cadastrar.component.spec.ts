import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntradasaidaCadastrarComponent } from './entradasaida-cadastrar.component';

describe('EntradasaidaCadastrarComponent', () => {
  let component: EntradasaidaCadastrarComponent;
  let fixture: ComponentFixture<EntradasaidaCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EntradasaidaCadastrarComponent]
    });
    fixture = TestBed.createComponent(EntradasaidaCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
