import { Component, OnInit } from '@angular/core';
import { AtividadeLudicaService } from '../service/atividadeludica.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { Situacoes } from 'src/app/shared/situacoes/situacoes';
import { MessageService } from 'primeng/api';
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-atividadeludica-cadastrar',
  templateUrl: './atividadeludica-cadastrar.component.html',
  styleUrls: ['./atividadeludica-cadastrar.component.css'],
  providers: [AtividadeLudicaService ]
})
export class AtividadeludicaCadastrarComponent implements OnInit {

  form: FormGroup;
  novo:boolean = false;
  opcoesSituacao: LabelValue[] = Situacoes;

  constructor(
    private route: ActivatedRoute,
    private atividadeLudicaService: AtividadeLudicaService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toastService: ToastService
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      nome: ['', [Validators.required, Validators.maxLength(255)]],
      dataHora: [new Date, Validators.required],
      situacao: ['ATIVO', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(c => {
      let id = c['id'];
      if (id) {
        this.carrega(Number(id));
      }
      this.novo = id ? false : true;
    })
  }

  salvar() {
    if (this.form.valid) {
      this.atividadeLudicaService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.toastService.toastSuccess(`Atividade lúdica ${this.novo ? 'salva' : 'atualizada'} com sucesso.`);
          setTimeout(() => {
            this.router.navigate(['/atividadeludica/listar']);
          }, 500);
        }
      })
      this.limpar();
    } else {
      this.form.markAllAsTouched();
      this.toastService.toastWarning('Não foi possível salvar!','Verifique os campos e tente novamente.');
    }
  }

  limpar() {
    this.form.reset();
  }

  voltar() {
    this.router.navigate(['/atividadeludica/listar']);
  }

  private carrega(id: number) {
    this.atividadeLudicaService.findByID(id).subscribe((atividadeLudica) => {
      this.form.patchValue(atividadeLudica)
      this.form.get('dataHora')?.patchValue(new Date(atividadeLudica.dataHora))
    });
  }

}
