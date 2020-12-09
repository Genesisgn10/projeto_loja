import { ClienteComponent } from './cliente-form/cliente.component';
import { AuthGuard } from './../seguranca/auth.guard';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'cliente', component: ClienteComponent,
    canActivate: [AuthGuard],
    data: { roles: ['DH01'] } 
  },
  { path: 'cliente/:codigo', component: ClienteComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClienteRoutingModule { }
