import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExameCadastrarComponent } from './exame-cadastrar.component';

describe('ExameCadastrarComponent', () => {
  let component: ExameCadastrarComponent;
  let fixture: ComponentFixture<ExameCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ExameCadastrarComponent]
    });
    fixture = TestBed.createComponent(ExameCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
