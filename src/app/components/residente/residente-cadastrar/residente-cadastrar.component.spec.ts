import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResidenteCadastrarComponent } from './residente-cadastrar.component';

describe('ResidenteCadastrarComponent', () => {
  let component: ResidenteCadastrarComponent;
  let fixture: ComponentFixture<ResidenteCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResidenteCadastrarComponent]
    });
    fixture = TestBed.createComponent(ResidenteCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
