import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterTestingModule } from "@angular/router/testing";
import { ButtonModule } from "primeng/button";
import { TooltipModule } from "primeng/tooltip";
import { ConfiguracaosistemaCadastrarComponent } from "./configuracaosistema-cadastrar.component";
import { MessageService } from "primeng/api";
import { CheckboxModule } from "primeng/checkbox";

describe('ConfiguracaosistemaCadastrarComponent', () => {
    let component: ConfiguracaosistemaCadastrarComponent;
    let fixture: ComponentFixture<ConfiguracaosistemaCadastrarComponent>;
  
    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [ConfiguracaosistemaCadastrarComponent],
        imports: [
          RouterTestingModule, 
          HttpClientTestingModule,
          FormsModule,
          ReactiveFormsModule,
          ButtonModule,
          TooltipModule,
          CheckboxModule,
        ],
        providers: [MessageService]
      });
      fixture = TestBed.createComponent(ConfiguracaosistemaCadastrarComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  
    it('should create', () => {
      expect(component).toBeTruthy();
    });
  });
  