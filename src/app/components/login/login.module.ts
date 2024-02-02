import { NgModule } from "@angular/core";
import { CardModule } from "primeng/card";
import { CommonModule } from "@angular/common";
import { ReactiveFormsModule } from "@angular/forms";
import { LoginComponent } from "./login.component";
import { ButtonModule } from "primeng/button";

@NgModule({
    declarations: [
        LoginComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        CardModule,
        ButtonModule,
    ]
})
export class LoginModule {}