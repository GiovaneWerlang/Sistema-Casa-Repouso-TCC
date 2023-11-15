import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { EspecialidadeService } from '../service/especialidade.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-especialidade-cadastrar',
  templateUrl: './especialidade-cadastrar.component.html',
  styleUrls: ['./especialidade-cadastrar.component.css'],
  providers: [EspecialidadeService, MessageService]
})
export class EspecialidadeCadastrarComponent implements OnInit {

  form: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private especialidadeService: EspecialidadeService,
    private formBuilder: FormBuilder,
    private router: Router,
    private messageService: MessageService
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      nome: ['', [Validators.required, Validators.maxLength(100)]],
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(c => {
      let id = c['id'];
      if (id != null) {
        this.carregaEspecialidade(Number(id));
      }
    })
  }

  salvar() {
    if (this.form.valid) {
      this.especialidadeService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.router.navigate(['/especialidade/listar']);
        }
      })
      this.limpar();
    } else {
      this.form.markAllAsTouched();
      this.messageService.add({ severity: 'warn', summary: 'Não foi possível salvar!', detail: 'Verifique os campos e tente novamente.' });
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
