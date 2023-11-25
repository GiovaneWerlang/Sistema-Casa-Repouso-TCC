import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeconsultaEditarComponent } from './atividadeconsulta-editar.component';

describe('AtividadeconsultaEditarComponent', () => {
  let component: AtividadeconsultaEditarComponent;
  let fixture: ComponentFixture<AtividadeconsultaEditarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividadeconsultaEditarComponent]
    });
    fixture = TestBed.createComponent(AtividadeconsultaEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
