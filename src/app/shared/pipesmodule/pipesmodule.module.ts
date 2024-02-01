import { NgModule } from "@angular/core";
import { CpfPipe } from "../cpf-pipe/cpf-pipe.pipe";
import { EnumPipe } from "../enum-pipe/enum-pipe.pipe";

@NgModule({
    imports: [
    ],
    declarations: [ 
      EnumPipe,
      CpfPipe
    ],
    exports: [
        EnumPipe,
        CpfPipe
    ]
  })
  export class PipesModule {}