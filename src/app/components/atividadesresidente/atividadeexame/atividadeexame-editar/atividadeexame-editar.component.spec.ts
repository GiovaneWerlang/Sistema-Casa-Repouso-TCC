import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeexameEditarComponent } from './atividadeexame-editar.component';

describe('AtividadeexameEditarComponent', () => {
  let component: AtividadeexameEditarComponent;
  let fixture: ComponentFixture<AtividadeexameEditarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividadeexameEditarComponent]
    });
    fixture = TestBed.createComponent(AtividadeexameEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
