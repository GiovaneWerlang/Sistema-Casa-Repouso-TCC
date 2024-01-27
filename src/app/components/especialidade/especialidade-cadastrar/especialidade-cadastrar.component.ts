import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { EspecialidadeService } from '../service/especialidade.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-especialidade-cadastrar',
  templateUrl: './especialidade-cadastrar.component.html',
  styleUrls: ['./especialidade-cadastrar.component.css'],
  providers: [EspecialidadeService]
})
export class EspecialidadeCadastrarComponent implements OnInit {

  form: FormGroup;
  novo:boolean = false;
  constructor(
    private route: ActivatedRoute,
    private especialidadeService: EspecialidadeService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toastService: ToastService
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      nome: ['', [Validators.required, Validators.maxLength(100)]],
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(c => {
      let id = c['id'];
      if (id) {
        this.carregaEspecialidade(Number(id));
      }
      this.novo = id ? false : true;
    })
  }

  salvar() {
    if (this.form.valid) {
      this.especialidadeService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) { 
          this.toastService.toastSuccess(`Especialidade  ${this.novo ? 'salva' : 'atualizada'}  com sucesso.`);
          this.router.navigate(['/especialidade/listar']);
        }
      })
      this.limpar();
    } else {
      this.form.markAllAsTouched();
      this.toastService.toastWarning('Não foi possível salvar!', 'Verifique os campos e tente novamente.');
    }
  }

  limpar() {
    this.form.reset();
  }

  voltar() {
    this.router.navigate(['/especialidade/listar']);
  }

  private carregaEspecialidade(id: number) {
    this.especialidadeService.findByID(id).subscribe((especialidade) => this.form.setValue(especialidade));
  }
}
