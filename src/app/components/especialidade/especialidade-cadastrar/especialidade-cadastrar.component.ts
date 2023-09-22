import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { EspecialidadeService } from '../service/especialidade.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-especialidade-cadastrar',
  templateUrl: './especialidade-cadastrar.component.html',
  styleUrls: ['./especialidade-cadastrar.component.css'],
  providers: [EspecialidadeService]
})
export class EspecialidadeCadastrarComponent implements OnInit {

  form: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private especialidadeService: EspecialidadeService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      nome: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(c => {
      let id = c['id'];
      if (id != null) {
        this.carregaEspecialidade(Number(id));
      }
    })
    console.log(environment.apiUrl)
  }

  salvar() {
    this.especialidadeService.save(this.form.getRawValue()).subscribe((res) => {
      console.log(res)
      if(res && res.id){
        this.router.navigate(['/especialidade/listar']);
      }
    })
    this.limpar();
  }

  limpar() {
    this.form.reset();
  }

  voltar(){
    this.router.navigate(['/especialidade/listar']);
  }

  private carregaEspecialidade(id: number) {
    this.especialidadeService.findByID(id).subscribe((especialidade) => this.form.setValue(especialidade));
  }
}
