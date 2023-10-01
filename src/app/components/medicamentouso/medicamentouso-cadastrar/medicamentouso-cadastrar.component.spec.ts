import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicamentousoCadastrarComponent } from './medicamentouso-cadastrar.component';

describe('MedicamentousoCadastrarComponent', () => {
  let component: MedicamentousoCadastrarComponent;
  let fixture: ComponentFixture<MedicamentousoCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MedicamentousoCadastrarComponent]
    });
    fixture = TestBed.createComponent(MedicamentousoCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
