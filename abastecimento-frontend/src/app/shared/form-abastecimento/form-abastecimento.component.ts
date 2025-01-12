import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Abastecimento } from '../confirmation-dialog/abastecimento.model';

@Component({
    selector: 'app-form-abastecimento',
    templateUrl: './form-abastecimento.component.html',
    styleUrls: ['./form-abastecimento.component.css']
})
export class FormAbastecimentoComponent {
    @Output() formSubmit = new EventEmitter<Abastecimento>();

    abastecimentoForm: FormGroup;

    constructor(private fb: FormBuilder) {
        this.abastecimentoForm = this.fb.group({
            dataHora: ['', Validators.required],
            placaVeiculo: ['', [Validators.required, Validators.pattern('^[A-Z]{3}-\\d{4}|[A-Z]{3}\\d[A-Z]\\d{2}$')]],
            quilometragem: ['', [Validators.required, Validators.min(0)]],
            valorTotal: ['', [Validators.required, Validators.min(0.01)]]
        });
    }

    onSubmit(): void {
        if (this.abastecimentoForm.valid) {
            this.formSubmit.emit(this.abastecimentoForm.value);
        }
    }

}