import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaListarComponent } from './consulta-listar.component';

describe('ConsultaListarComponent', () => {
  let component: ConsultaListarComponent;
  let fixture: ComponentFixture<ConsultaListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConsultaListarComponent]
    });
    fixture = TestBed.createComponent(ConsultaListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
