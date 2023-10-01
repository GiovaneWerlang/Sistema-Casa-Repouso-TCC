import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExameListarComponent } from './exame-listar.component';

describe('ExameListarComponent', () => {
  let component: ExameListarComponent;
  let fixture: ComponentFixture<ExameListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ExameListarComponent]
    });
    fixture = TestBed.createComponent(ExameListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
