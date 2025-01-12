import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AbastecimentoListComponent } from './abastecimento-list/abastecimento-list.component';
import { AbastecimentoAddComponent } from './abastecimento-add/abastecimento-add.component';

const routes: Routes = [
  { path: '', component: AbastecimentoListComponent },
  { path: 'add', component: AbastecimentoAddComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }