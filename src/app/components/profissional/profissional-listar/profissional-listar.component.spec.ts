import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalListarComponent } from './profissional-listar.component';

describe('ProfissionalListarComponent', () => {
  let component: ProfissionalListarComponent;
  let fixture: ComponentFixture<ProfissionalListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProfissionalListarComponent]
    });
    fixture = TestBed.createComponent(ProfissionalListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
