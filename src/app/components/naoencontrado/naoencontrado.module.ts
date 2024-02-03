import { NgModule } from "@angular/core";
import { CardModule } from "primeng/card";
import { CommonModule } from "@angular/common";
import { NaoencontradoComponent } from "./naoencontrado.component";
import { ButtonModule } from "primeng/button";
import { NaoEncontradoRoutingModule } from "./naoencontrado-routing.module";

@NgModule({
    declarations: [
        NaoencontradoComponent
    ],
    imports: [
        CommonModule,
        CardModule,
        ButtonModule,
        NaoEncontradoRoutingModule,
    ]
})
export class NaoEncontradoModule {}