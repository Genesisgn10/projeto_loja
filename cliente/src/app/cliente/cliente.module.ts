import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { ClienteComponent } from './../cliente/cliente-form/cliente.component';
import { FileUploadModule } from 'primeng/fileupload';
import { InputMaskModule } from 'primeng/inputmask';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClienteRoutingModule } from './cliente-routing.module';
import { ClienteTableComponent } from './cliente-table/cliente-table.component';


@NgModule({
  declarations: [
    ClienteComponent,
    ClienteTableComponent
  ],
  exports:[
    ClienteComponent
  ],
  imports: [
    CommonModule,
    ClienteRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    DropdownModule,
    BrowserAnimationsModule,
    InputMaskModule,
    FileUploadModule,
    
  ],
  providers: [
    
  ],
})
export class ClienteModule { }
