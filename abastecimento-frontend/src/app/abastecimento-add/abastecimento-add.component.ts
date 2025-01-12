import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Abastecimento } from '../shared/confirmation-dialog/abastecimento.model';
import { AbastecimentoService } from '../shared/abastecimento.service';

@Component({
  selector: 'app-abastecimento-add',
  templateUrl: './abastecimento-add.component.html',
  styleUrls: ['./abastecimento-add.component.css']
})
export class AbastecimentoAddComponent {
    constructor(private abastecimentoService: AbastecimentoService,
                private router: Router) { }

    onSubmit(abastecimento: Abastecimento): void {
        this.abastecimentoService.add(abastecimento).subscribe(() => {
            this.router.navigate(['/']);
        });
    }
}