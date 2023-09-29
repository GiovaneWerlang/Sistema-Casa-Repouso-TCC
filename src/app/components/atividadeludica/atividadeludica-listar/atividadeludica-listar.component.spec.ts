import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeludicaListarComponent } from './atividadeludica-listar.component';

describe('AtividadeludicaListarComponent', () => {
  let component: AtividadeludicaListarComponent;
  let fixture: ComponentFixture<AtividadeludicaListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividadeludicaListarComponent]
    });
    fixture = TestBed.createComponent(AtividadeludicaListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
