import { Component } from '@angular/core';
import { ConsultaService } from '../service/consulta.service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfissionalService } from '../../profissional/service/profissional.service';
import { EspecialidadeService } from '../../especialidade/service/especialidade.service';
import { ResidenteService } from '../../residente/service/residente.service';
import { Especialidade } from '../../especialidade/modelo/especialidade';
import { Profissional } from '../../profissional/modelo/profissional';
import { Residente } from '../../residente/modelo/residente';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-consulta-cadastrar',
  templateUrl: './consulta-cadastrar.component.html',
  styleUrls: ['./consulta-cadastrar.component.css'],
  providers: [ConsultaService, MessageService]
})
export class ConsultaCadastrarComponent {
  form: FormGroup;
  opcoesEspecialidade: LabelValue[] = [];
  opcoesProfissional: LabelValue[] = [];
  opcoesResidente: LabelValue[] = [];

  constructor(
    private route: ActivatedRoute,
    private consultaService: ConsultaService,
    private profissionalService: ProfissionalService,
    private especialidadeService: EspecialidadeService,
    private residenteService: ResidenteService,
    private formBuilder: FormBuilder,
    private router: Router,
    private messageService: MessageService
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      descricao: ['', [Validators.required, Validators.maxLength(255)]],
      dataHora: [null, Validators.required],
      local: ['', [Validators.required, Validators.maxLength(100)]],
      prescricao: ['', Validators.maxLength(255)],
      especialidade: [null, Validators.required],
      profissional: [null, Validators.required],
      residente: [null, Validators.required]
    });
    this.carregarOpcoesEspecialidade();
    this.carregarOpcoesProfissional();
    this.carregarOpcoesResidente();
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
      this.consultaService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.router.navigate(['/consulta/listar']);
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
    this.router.navigate(['/consulta/listar']);
  }

  private carrega(id: number) {
    this.consultaService.findByID(id).subscribe((consulta) => {
      this.form.patchValue(consulta);
      this.form.get('dataHora')?.patchValue(new Date(consulta.dataHora));

      this.form.get('especialidade')?.patchValue(consulta.especialidade.id);
      this.form.get('profissional')?.patchValue(consulta.profissional.id);
      this.form.get('residente')?.patchValue(consulta.residente.id);
    });
  }

  private carregarOpcoesEspecialidade() {
    this.especialidadeService.list().subscribe((res) =>
      this.opcoesEspecialidade = res?.map((i: Especialidade) => ({ label: i.id + ' - ' + i.nome, value: i.id }))
    );
  }

  private carregarOpcoesProfissional() {
    this.profissionalService.list().subscribe((res) =>
      this.opcoesProfissional = res?.map((i: Profissional) => ({ label: i.id + ' - ' + i.nome, value: i.id }))
    );
  }

  private carregarOpcoesResidente() {
    this.residenteService.list().subscribe((res) =>
      this.opcoesResidente = res?.map((i: Residente) => ({ label: i.id + ' - ' + i.nome, value: i.id }))
    );
  }
}
