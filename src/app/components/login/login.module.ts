import { NgModule } from "@angular/core";
import { CardModule } from "primeng/card";
import { CommonModule } from "@angular/common";
import { ReactiveFormsModule } from "@angular/forms";
import { LoginComponent } from "./login.component";
import { ButtonModule } from "primeng/button";
import { LoginRoutingModule } from "./login-routing.module";

@NgModule({
    declarations: [
        LoginComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        CardModule,
        ButtonModule,
        LoginRoutingModule,
    ]
})
export class LoginModule {}