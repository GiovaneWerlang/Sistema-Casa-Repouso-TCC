import { Component, OnInit } from '@angular/core';
import { ResidenteService } from '../service/residente.service';
import { TipoEstadia } from 'src/app/shared/tipoestadia/tipoestadia';
import { Situacoes } from 'src/app/shared/situacoes/situacoes';
import { Estados } from 'src/app/shared/estados/estados';
import { Paises } from 'src/app/shared/paises/paises';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-residente-cadastrar',
  templateUrl: './residente-cadastrar.component.html',
  styleUrls: ['./residente-cadastrar.component.css'],
  providers: [ResidenteService]
})
export class ResidenteCadastrarComponent implements OnInit {

  form: FormGroup;
  novo:boolean = false;
  formEndereco: FormGroup;
  opcoesSituacao: LabelValue[] = Situacoes;
  tiposEstadia: LabelValue[] = TipoEstadia;
  opcoesEstados: string[] = Estados;
  opcoesPaises: string[] = Paises;

  constructor(
    private route: ActivatedRoute,
    private residenteService: ResidenteService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toastService: ToastService
  ) {
    this.formEndereco = this.formBuilder.group({
      logradouro: ['', [Validators.required, Validators.maxLength(150)]],
      numero: ['', [Validators.required, Validators.maxLength(50)]],
      bairro: ['', [Validators.required, Validators.maxLength(100)]],
      municipio: ['', [Validators.required, Validators.maxLength(150)]],
      cep: ['', [Validators.required, Validators.maxLength(8), Validators.minLength(8)]],
      estado: ['Acre', Validators.required],
      pais: ['Brasil', Validators.required]
    });
    this.form = this.formBuilder.group({
      id: [null],
      nome: ['', [Validators.required, Validators.maxLength(150)]],
      idade: [0, [Validators.required, Validators.min(1), Validators.max(140)]],
      cpf: ['', Validators.required],
      telefone: ['', [Validators.required, Validators.maxLength(11), Validators.minLength(10)]],
      email: ['', [Validators.required, Validators.maxLength(100)]],
      tipoEstadia: ['PADRAO', Validators.required],
      dataHoraIngresso: [new Date, Validators.required],
      dataHoraPrevisaoSaida: [null],
      situacao: ['ATIVO', Validators.required],
      endereco: this.formEndereco
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
      this.residenteService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.toastService.toastSuccess(`Residente ${this.novo ? 'salvo(a)' : 'atualizado(a)'} com sucesso.`);
          this.router.navigate(['/residente/listar']);
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
    this.router.navigate(['/residente/listar']);
  }

  private carrega(id: number) {
    this.residenteService.findByID(id).subscribe((residente) => {
      this.form.patchValue(residente);
      if (residente.dataHoraIngresso) {
        this.form.get('dataHoraIngresso')?.patchValue(new Date(residente.dataHoraIngresso));
      }
      if (residente.dataHoraPrevisaoSaida) {
        this.form.get('dataHoraPrevisaoSaida')?.patchValue(new Date(residente.dataHoraPrevisaoSaida));
      }

      this.form.get('logradouro')?.patchValue(residente.endereco.logradouro);
      this.form.get('bairro')?.patchValue(residente.endereco.bairro);
      this.form.get('municipio')?.patchValue(residente.endereco.municipio);
      this.form.get('numero')?.patchValue(residente.endereco.numero);
      this.form.get('cep')?.patchValue(residente.endereco.cep);
      this.form.get('estado')?.patchValue(residente.endereco.estado);
      this.form.get('pais')?.patchValue(residente.endereco.pais);
    });
  }

}
