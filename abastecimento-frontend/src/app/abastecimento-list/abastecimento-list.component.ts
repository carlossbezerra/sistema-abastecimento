import { Component, OnInit } from '@angular/core';
import { Abastecimento } from '../shared/confirmation-dialog/abastecimento.model';
import { AbastecimentoService } from '../shared/abastecimento.service';
import { PageEvent } from '@angular/material/paginator';
  import { MatDialog } from '@angular/material/dialog';
 import { ConfirmationDialogComponent } from '../shared/confirmation-dialog/confirmation-dialog.component';
@Component({
  selector: 'app-abastecimento-list',
  templateUrl: './abastecimento-list.component.html',
  styleUrls: ['./abastecimento-list.component.css']
})
export class AbastecimentoListComponent implements OnInit {

  abastecimentos: Abastecimento[] = [];
  totalElements: number = 0;
  pageSize: number = 5;
  pageIndex: number = 0;
  placaFilter: string = '';
    displayedColumns: string[] = ['id', 'dataHora', 'placaVeiculo', 'quilometragem', 'valorTotal', 'actions'];

  constructor(private abastecimentoService: AbastecimentoService,
               private dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadAbastecimentos();
  }

  loadAbastecimentos(): void {
    this.abastecimentoService.getAll(this.pageIndex, this.pageSize, this.placaFilter)
      .subscribe(page => {
        this.abastecimentos = page.content;
        this.totalElements = page.totalElements;
      });
  }

  handlePageEvent(event: PageEvent): void {
    this.pageSize = event.pageSize;
    this.pageIndex = event.pageIndex;
    this.loadAbastecimentos();
  }

    applyFilter(): void {
    this.pageIndex = 0;
    this.loadAbastecimentos();
    }

    remove(id: number): void {
        const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
            width: '250px',
            data: { message: 'Tem certeza que deseja remover este abastecimento?' }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.abastecimentoService.delete(id).subscribe(() => {
                    this.loadAbastecimentos();
                });
            }
        });
    }
}