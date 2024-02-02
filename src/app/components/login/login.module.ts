import { NgModule } from "@angular/core";
import { CardModule } from "primeng/card";
import { CommonModule } from "@angular/common";
import { ReactiveFormsModule } from "@angular/forms";
import { LoginComponent } from "./login.component";
import { ButtonModule } from "primeng/button";
import { LoginRoutingModule } from "./login-routing.module";
import { InputTextModule } from "primeng/inputtext";

@NgModule({
    declarations: [
        LoginComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        CardModule,
        InputTextModule,
        ButtonModule,
        LoginRoutingModule,
    ]
})
export class LoginModule {}