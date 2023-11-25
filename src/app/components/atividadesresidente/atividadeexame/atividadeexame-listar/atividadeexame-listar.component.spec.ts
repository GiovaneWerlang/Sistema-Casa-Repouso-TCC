import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeexameListarComponent } from './atividadeexame-listar.component';

describe('AtividadeexameListarComponent', () => {
  let component: AtividadeexameListarComponent;
  let fixture: ComponentFixture<AtividadeexameListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividadeexameListarComponent]
    });
    fixture = TestBed.createComponent(AtividadeexameListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
