import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntradasaidaListarComponent } from './entradasaida-listar.component';

describe('EntradasaidaListarComponent', () => {
  let component: EntradasaidaListarComponent;
  let fixture: ComponentFixture<EntradasaidaListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EntradasaidaListarComponent]
    });
    fixture = TestBed.createComponent(EntradasaidaListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
