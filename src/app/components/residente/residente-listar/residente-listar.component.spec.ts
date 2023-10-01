import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResidenteListarComponent } from './residente-listar.component';

describe('ResidenteListarComponent', () => {
  let component: ResidenteListarComponent;
  let fixture: ComponentFixture<ResidenteListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResidenteListarComponent]
    });
    fixture = TestBed.createComponent(ResidenteListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
