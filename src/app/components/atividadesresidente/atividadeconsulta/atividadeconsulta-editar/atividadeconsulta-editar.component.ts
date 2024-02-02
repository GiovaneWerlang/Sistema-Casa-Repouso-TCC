import { Component } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AutenticacaoService } from 'src/app/components/login/service/autenticacao.service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { SituacaoAtividade } from 'src/app/shared/situacaoatividade/situacaoatividade';
import { AtividadeResidente } from '../../modelo/atividaderesidente';
import { AtividadeConsultaService } from '../service/atividadeconsulta.service';
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-atividadeconsulta-editar',
  templateUrl: './atividadeconsulta-editar.component.html',
  styleUrls: ['./atividadeconsulta-editar.component.css']
})
export class AtividadeconsultaEditarComponent {
  form: FormGroup;
  novo:boolean = false;
  opcoessituacao: LabelValue[] = SituacaoAtividade;

  constructor(
    private route: ActivatedRoute,
    private atividadeConsultaService: AtividadeConsultaService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toastService: ToastService,
    private autenticacaoService: AutenticacaoService
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      descricao: [{ value: '', disabled: true }],
      dataHora: [{ value: null, disabled: true }],
      situacao: [''],
      profissional: [{ value: null, disabled: true }],
      local: [{ value: null, disabled: true }],
      especialidade: [{ value: null, disabled: true }],
      residente: [{ value: null, disabled: true }]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(c => {
      let id = c['id'];
      if (id) {
        this.carrega(Number(id));
      }
    })
  }

  salvar() {
    if (this.form.valid) {
      this.atividadeConsultaService.updateSituacao(
        this.form.get('id')?.value,
        this.montaAtividadeResidente()
      ).subscribe((res) => {
        if (res) {
          this.toastService.toastSuccess('Atividade de consulta atualizada com sucesso.');
          this.voltar();
        }
      })
      this.limpar();
    } else {
      this.form.markAllAsTouched();
      this.toastService.toastWarning('Não foi possível atualizar!', 'Verifique os campos e tente novamente.');
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
    this.router.navigate([`/${this.atividadeConsultaService.getUrl()}/listar`]);
  }

  private carrega(id: number) {
    this.atividadeConsultaService.findByID(id).subscribe((atividadeconsulta) => {
      this.form.patchValue(atividadeconsulta);
      this.form.get('dataHora')?.patchValue(new Date(atividadeconsulta.dataHora));
      if (atividadeconsulta.profissional) {
        this.form.get('profissional')?.patchValue(atividadeconsulta.profissional.id + ' - ' + atividadeconsulta.profissional.nome);
      } else if (this.autenticacaoService?.dadoUsuario?.value) {
        this.form.get('profissional')?.patchValue(this.autenticacaoService.dadoUsuario?.value?.id + ' - ' + this.autenticacaoService.dadoUsuario?.value?.nome);
      }
      this.form.get('local')?.patchValue(atividadeconsulta.consulta.local);
      this.form.get('residente')?.patchValue(atividadeconsulta.consulta.residente.id + ' - ' + atividadeconsulta.consulta.residente.nome);
      this.form.get('especialidade')?.patchValue(atividadeconsulta.consulta.especialidade.id + ' - ' + atividadeconsulta.consulta.especialidade.nome);
    });
  }
}