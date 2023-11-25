import { Component } from '@angular/core';
import { AtividadeExameService } from '../service/atividadeexame.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AutenticacaoService } from 'src/app/components/login/service/autenticacao.service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { SituacaoAtividade } from 'src/app/shared/situacaoatividade/situacaoatividade';
import { AtividadeResidente } from '../../modelo/atividaderesidente';

@Component({
  selector: 'app-atividadeexame-editar',
  templateUrl: './atividadeexame-editar.component.html',
  styleUrls: ['./atividadeexame-editar.component.css']
})
export class AtividadeexameEditarComponent {
  form: FormGroup;

  opcoessituacao: LabelValue[] = SituacaoAtividade;

  constructor(
    private route: ActivatedRoute,
    private atividadeExameService: AtividadeExameService,
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
      local: [{ value: null, disabled: true }],
      especialidade: [{ value: null, disabled: true }],
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
      this.atividadeExameService.updateSituacao(
        this.form.get('id')?.value,
        this.montaAtividadeResidente()
      ).subscribe((res) => {
        if (res) {
          this.router.navigate(['/atividadeexame/listar']);
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
    this.router.navigate(['/atividadeexame/listar']);
  }

  private carrega(id: number) {
    this.atividadeExameService.findByID(id).subscribe((atividadeexame) => {
      this.form.patchValue(atividadeexame);
      this.form.get('dataHora')?.patchValue(new Date(atividadeexame.dataHora));
      if (atividadeexame.profissional) {
        this.form.get('profissional')?.patchValue(atividadeexame.profissional.id + ' - ' + atividadeexame.profissional.nome);
      } else if (this.autenticacaoService?.dadoUsuario?.value) {
        this.form.get('profissional')?.patchValue(this.autenticacaoService.dadoUsuario?.value?.id + ' - ' + this.autenticacaoService.dadoUsuario?.value?.nome);
      }
      this.form.get('local')?.patchValue(atividadeexame.exame.local);
      this.form.get('residente')?.patchValue(atividadeexame.exame.residente.id + ' - ' + atividadeexame.exame.residente.nome);
      this.form.get('especialidade')?.patchValue(atividadeexame.exame.especialidade.id + ' - ' + atividadeexame.exame.especialidade.nome);

    });
  }
}
