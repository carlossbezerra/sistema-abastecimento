import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {  provideHttpClient, withFetch  } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { AbastecimentoListComponent } from './abastecimento-list/abastecimento-list.component';
import { AbastecimentoAddComponent } from './abastecimento-add/abastecimento-add.component';
import { FormAbastecimentoComponent } from './shared/form-abastecimento/form-abastecimento.component';
import { ConfirmationDialogComponent } from './shared/confirmation-dialog/confirmation-dialog.component';
import { RouterModule } from '@angular/router';


@NgModule({
    declarations: [
    AppComponent,
    AbastecimentoListComponent,
    AbastecimentoAddComponent,
    FormAbastecimentoComponent,
    ConfirmationDialogComponent
    ],
    imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatDialogModule,
    RouterModule
    ],
    providers: [
    provideHttpClient(withFetch())
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
