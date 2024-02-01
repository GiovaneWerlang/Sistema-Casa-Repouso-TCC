import { NgModule } from "@angular/core";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { ButtonModule } from "primeng/button";
import { ReactiveFormsModule } from "@angular/forms";
import { TableModule } from "primeng/table";
import { PaginatorModule } from "primeng/paginator";
import { ProgressSpinnerModule } from "primeng/progressspinner";
import { BlockUIModule } from "primeng/blockui";
import { CommonModule } from "@angular/common";
import { CrudTableComponent } from "./crud-table.component";

@NgModule({
    declarations: [
        CrudTableComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        ButtonModule,
        ConfirmDialogModule,
        ProgressSpinnerModule,
        BlockUIModule,
        TableModule,
        PaginatorModule,
    ],
    exports: [
        CrudTableComponent
    ]
})
export class CrudTableModule {}