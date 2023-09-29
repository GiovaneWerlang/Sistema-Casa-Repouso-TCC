import { Component, OnInit } from '@angular/core';
import { AtividadeLudicaService } from '../service/atividadeludica.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { Situacoes } from 'src/app/shared/situacoes/situacoes';

@Component({
  selector: 'app-atividadeludica-cadastrar',
  templateUrl: './atividadeludica-cadastrar.component.html',
  styleUrls: ['./atividadeludica-cadastrar.component.css'],
  providers: [AtividadeLudicaService]
})
export class AtividadeludicaCadastrarComponent implements OnInit {

  form: FormGroup;
  opcoesSituacao:LabelValue[] = Situacoes;

  constructor(
    private route: ActivatedRoute,
    private atividadeLudicaService: AtividadeLudicaService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      nome: ['', Validators.required],
      dataHora: [new Date, Validators.required],
      situacao: ['ATIVO',Validators.required]
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
    this.atividadeLudicaService.save(this.form.getRawValue()).subscribe((res) => {
      if(res){
        this.router.navigate(['/atividadeludica/listar']);
      }
    })
    this.limpar();
  }

  limpar() {
    this.form.reset();
  }

  voltar(){
    this.router.navigate(['/atividadeludica/listar']);
  }

  private carrega(id: number) {
    this.atividadeLudicaService.findByID(id).subscribe((atividadeLudica) => {
      this.form.patchValue(atividadeLudica)
      this.form.get('dataHora')?.patchValue(new Date(atividadeLudica.dataHora))
    });
  }

}
