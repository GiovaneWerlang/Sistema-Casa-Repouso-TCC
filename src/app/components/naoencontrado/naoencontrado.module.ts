import { NgModule } from "@angular/core";
import { CardModule } from "primeng/card";
import { CommonModule } from "@angular/common";
import { NaoencontradoComponent } from "./naoencontrado.component";

@NgModule({
    declarations: [
        NaoencontradoComponent
    ],
    imports: [
        CommonModule,
        CardModule
    ]
})
export class NaoEncontradoModule {}