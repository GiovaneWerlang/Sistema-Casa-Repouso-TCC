import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { HeaderComponent } from "./header.component";
import { SideMenuModule } from "../side-menu/side-menu.module";
import { ButtonModule } from "primeng/button";
import { MenuModule } from "primeng/menu";

@NgModule({
    declarations: [
        HeaderComponent
    ],
    imports: [
        CommonModule,
        SideMenuModule,
        MenuModule,
        ButtonModule,
    ],
    exports: [
        HeaderComponent
    ]
})
export class HeaderModule {}