import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SideMenuComponent } from "./side-menu.component";
import { SidebarModule } from "primeng/sidebar";
import { ButtonModule } from "primeng/button";
import { MenuItemsModule } from "../menu/menu.module";

@NgModule({
    declarations: [
        SideMenuComponent
    ],
    imports: [
        CommonModule,
        SidebarModule,
        ButtonModule,
        MenuItemsModule
    ],
    exports: [
        SideMenuComponent
    ]
})
export class SideMenuModule {}