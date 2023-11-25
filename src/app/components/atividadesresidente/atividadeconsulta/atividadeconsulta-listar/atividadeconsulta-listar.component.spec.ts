import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeconsultaListarComponent } from './atividadeconsulta-listar.component';

describe('AtividadeconsultaListarComponent', () => {
  let component: AtividadeconsultaListarComponent;
  let fixture: ComponentFixture<AtividadeconsultaListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividadeconsultaListarComponent]
    });
    fixture = TestBed.createComponent(AtividadeconsultaListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
