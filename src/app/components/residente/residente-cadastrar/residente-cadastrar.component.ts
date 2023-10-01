import { Component, OnInit } from '@angular/core';
import { ResidenteService } from '../service/residente.service';
import { TipoEstadia } from 'src/app/shared/tipoestadia/tipoestadia';
import { Situacoes } from 'src/app/shared/situacoes/situacoes';
import { Estados } from 'src/app/shared/estados/estados';
import { Paises } from 'src/app/shared/paises/paises';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-residente-cadastrar',
  templateUrl: './residente-cadastrar.component.html',
  styleUrls: ['./residente-cadastrar.component.css'],
  providers: [ResidenteService]
})
export class ResidenteCadastrarComponent implements OnInit {

  form: FormGroup;
  opcoesSituacao:LabelValue[] = Situacoes;
  tiposEstadia:LabelValue[] = TipoEstadia;
  opcoesEstados:string[] = Estados;
  opcoesPaises:string[] = Paises;

  constructor(
    private route: ActivatedRoute,
    private residenteService: ResidenteService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      nome: ['', Validators.required],
      idade: [0, Validators.required],
      cpf: ['', Validators.required],
      telefone: ['', Validators.required],
      email: ['', Validators.required],
      tipoEstadia: ['PADRAO',Validators.required],
      dataHoraIngresso: [new Date, Validators.required],
      dataHoraPrevisaoSaida: [null],
      situacao: ['ATIVO',Validators.required],
      endereco: this.formBuilder.group({
      logradouro: ['', Validators.required],
      numero: ['', Validators.required],
      bairro: ['', Validators.required],
      municipio: ['', Validators.required],
      cep: ['', Validators.required],
      estado: ['', Validators.required],
      pais: ['Brasil', Validators.required]
      })
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
    this.residenteService.save(this.form.getRawValue()).subscribe((res) => {
      if(res){
        this.router.navigate(['/residente/listar']);
      }
    })
    this.limpar();
  }

  limpar() {
    this.form.reset();
  }

  voltar(){
    this.router.navigate(['/residente/listar']);
  }

  private carrega(id: number) {
    this.residenteService.findByID(id).subscribe((residente) => {
      this.form.patchValue(residente);
      this.form.get('dataHoraIngresso')?.patchValue(new Date(residente.dataHoraIngresso));
      this.form.get('dataHoraPrevisaoSaida')?.patchValue(new Date(residente.dataHoraPrevisaoSaida));

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
