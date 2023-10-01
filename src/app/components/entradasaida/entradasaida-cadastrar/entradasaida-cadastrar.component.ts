import { Component } from '@angular/core';
import { EntradasaidaService } from '../service/entradasaida.service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ResidenteService } from '../../residente/service/residente.service';
import { Residente } from '../../residente/modelo/residente';

@Component({
  selector: 'app-entradasaida-cadastrar',
  templateUrl: './entradasaida-cadastrar.component.html',
  styleUrls: ['./entradasaida-cadastrar.component.css'],
  providers: [EntradasaidaService]
})
export class EntradasaidaCadastrarComponent {
  form: FormGroup;
  opcoesResidente:LabelValue[] = [];

  constructor(
    private route: ActivatedRoute,
    private entradaSaidaService: EntradasaidaService,
    private residenteService: ResidenteService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      dataHoraEntrada: [null],
      dataHoraSaida: [null],
      descricao: [''],
      residente: ['', Validators.required],
    });
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
    this.entradaSaidaService.save(this.form.getRawValue()).subscribe((res) => {
      if(res){
        this.router.navigate(['/entradasaida/listar']);
      }
    })
    this.limpar();
  }

  limpar() {
    this.form.reset();
  }

  voltar(){
    this.router.navigate(['/entradasaida/listar']);
  }

  private carrega(id: number) {
    this.entradaSaidaService.findByID(id).subscribe((entradaSaida) => {
      this.form.patchValue(entradaSaida)
      this.form.get('dataHoraEntrada')?.patchValue(new Date(entradaSaida.dataHoraEntrada));
      this.form.get('dataHoraSaida')?.patchValue(new Date(entradaSaida.dataHoraSaida));
    });
  }

  private carregarOpcoesResidente(){
    this.residenteService.list().subscribe((res) => 
      this.opcoesResidente = res?.map((i:Residente) => ({label:i.id + ' - ' + i.nome, value:i.id}))
    );
  }
  
}
