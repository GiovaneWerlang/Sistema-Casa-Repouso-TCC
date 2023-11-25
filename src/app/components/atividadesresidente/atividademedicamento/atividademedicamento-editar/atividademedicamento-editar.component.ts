import { AutenticacaoService } from './../../../login/service/autenticacao.service';
import { Component } from '@angular/core';
import { AtividadeMedicamentoService } from '../service/atividademedicamento.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { SituacaoAtividade } from 'src/app/shared/situacaoatividade/situacaoatividade';
import { AtividadeResidente } from '../../modelo/atividaderesidente';

@Component({
  selector: 'app-atividademedicamento-editar',
  templateUrl: './atividademedicamento-editar.component.html',
  styleUrls: ['./atividademedicamento-editar.component.css']
})
export class AtividademedicamentoEditarComponent {
  form: FormGroup;

  opcoessituacao: LabelValue[] = SituacaoAtividade;

  constructor(
    private route: ActivatedRoute,
    private atividadeMedicamentoService: AtividadeMedicamentoService,
    private formBuilder: FormBuilder,
    private router: Router,
    private messageService: MessageService,
    private autenticacaoService: AutenticacaoService
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      descricao: [{ value: '', disabled: true }],
      dataHora: [{ value: null, disabled: true }],
      situacao: [''],
      profissional: [{ value: null, disabled: true }],
      medicamento: [{ value: null, disabled: true }],
      residente: [{ value: null, disabled: true }]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(c => {
      let id = c['id'];
      if (id != null) {
        this.carrega(Number(id));
      }
    })
  }

  salvar() {
    if (this.form.valid) {
      this.atividadeMedicamentoService.updateSituacao(
        this.form.get('id')?.value,
        this.montaAtividadeResidente()
      ).subscribe((res) => {
        if (res) {
          this.router.navigate(['/atividademedicamento/listar']);
        }
      })
      this.limpar();
    } else {
      this.form.markAllAsTouched();
      this.messageService.add({ severity: 'warn', summary: 'Não foi possível salvar!', detail: 'Verifique os campos e tente novamente.' });
    }
  }

  montaAtividadeResidente(): AtividadeResidente {
    let atividadeResidente: AtividadeResidente = {
      situacao: this.form.get('situacao')?.value,
      profissional: this.autenticacaoService?.dadoUsuario?.value ? this.autenticacaoService.dadoUsuario?.value?.id : 0
    }
    return atividadeResidente;
  }

  limpar() {
    this.form.reset();
  }

  voltar() {
    this.router.navigate(['/atividademedicamento/listar']);
  }

  private carrega(id: number) {
    this.atividadeMedicamentoService.findByID(id).subscribe((atividademedicamento) => {
      this.form.patchValue(atividademedicamento);
      this.form.get('dataHora')?.patchValue(new Date(atividademedicamento.dataHora));
      if (atividademedicamento.profissional) {
        this.form.get('profissional')?.patchValue(atividademedicamento.profissional.id + ' - ' + atividademedicamento.profissional.nome);
      } else if (this.autenticacaoService?.dadoUsuario?.value) {
        this.form.get('profissional')?.patchValue(this.autenticacaoService.dadoUsuario?.value?.id + ' - ' + this.autenticacaoService.dadoUsuario?.value?.nome);
      }
      this.form.get('medicamento')?.patchValue(atividademedicamento.medicamento.medicamento.id + ' - ' + atividademedicamento.medicamento.medicamento.nome);
      this.form.get('residente')?.patchValue(atividademedicamento.medicamento.residente.id + ' - ' + atividademedicamento.medicamento.residente.nome);

    });
  }
}
