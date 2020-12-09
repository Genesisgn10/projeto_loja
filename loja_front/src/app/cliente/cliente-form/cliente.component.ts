import { ActivatedRoute } from '@angular/router';
import { ClienteModel } from './../model/cliente-model';
import { ClienteRepository } from './../repository/cliente-repository';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Title } from '@angular/platform-browser';


@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})

export class ClienteComponent implements OnInit {
  @ViewChild('upload') upload: ElementRef;
  public formulario: FormGroup;
  estados: any[] = [];
  cidades: any[] = [];
  imagem: number;
  public submitted: boolean = false;
  uploadedFiles: any[] = [];
  operacao: boolean = true;
  constructor(
    private repository: ClienteRepository,
    private fb: FormBuilder,
    private messageService: MessageService,
    private route: ActivatedRoute,
    private title: Title) {

  }

  
  ngOnInit(): void {
    this.iniciarFormulario();
    this.listarEstados();
    const codigoCliente = this.route.snapshot.params['codigo'];
    console.log("codigo:"+codigoCliente);
    this.title.setTitle('Novo cliente');
    if (codigoCliente) {
      this.operacao = false;
      this.carregarCliente(codigoCliente);
    }
  }
  carregarCliente(codigoCliente: number) {
    this.repository.getClienteById(codigoCliente).subscribe(resposta => {
      this.formulario.controls.id.setValue(resposta.id);
      this.formulario.controls.nome.setValue(resposta.nome);
      this.formulario.controls.sobrenome.setValue(resposta.sobrenome);
      this.formulario.controls.telefones.setValue(resposta.telefones[0].telefone);
      this.formulario.controls.dataNasc.setValue(resposta.dataNasc);
      this.formulario.controls.cpf.setValue(resposta.cpf);
      this.formulario.controls.rg.setValue(resposta.rg);
      this.formulario.controls.email.setValue(resposta.email);
      this.formulario.controls.cep.setValue(resposta.endereco.cep);
      this.formulario.controls.logradouro.setValue(resposta.endereco.logradouro);
      this.formulario.controls.numero.setValue(resposta.endereco.numero);
      this.formulario.controls.complemento.setValue(resposta.endereco.complemento);
      this.formulario.controls.bairro.setValue(resposta.endereco.bairro);
      this.formulario.controls.estado.setValue(resposta.endereco.cidade.estado.id);
      this.listarCidadeSelecionada(resposta.endereco.cidade.id);
      this.imagem = resposta.foto.id;
      this.title.setTitle(`Edição do cliente: ${this.formulario.value.nome}`);
    });
  }
  public iniciarFormulario() {
    this.formulario = this.fb.group({
      id: [null],
      nome: ['', Validators.required],
      sobrenome: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(10)]],
      telefones: [''],
      dataNasc: [''],
      cpf: [''],
      rg: [''],
      email: ['', Validators.email],
      cep: [''],
      logradouro: [''],
      numero: [''],
      complemento: [''],
      bairro: [''],
      cidade: [''],
      estado: [''],
    });
  }
  cadastrar() {
    this.submitted = true;
    if (this.formulario.invalid) {
      return;
    }
    this.salvar();
  };
  salvar() {
    if (this.imagem && !this.uploadedFiles[0]) {
      this.salvarOuAtualizar();
    } else {
      const foto: any = this.uploadedFiles[0];
      const formData: any = new FormData();
      formData.append('imagem', foto);
      this.repository.postImagem(formData).subscribe(resposta => {
        this.imagem = resposta.id;
        this.salvarOuAtualizar();
      });
    }
  }
  salvarOuAtualizar() {
    const dados = {
      id: this.formulario.value.id,
      nome: this.formulario.value.nome,
      sobrenome: this.formulario.value.sobrenome,
      telefones: [{
        id: null,
        telefone: this.formulario.value.telefones,
        tipo: 'Casa'
      }],
      dataNasc: this.formulario.value.dataNasc,
      cpf: this.formulario.value.cpf,
      rg: this.formulario.value.rg,
      email: this.formulario.value.email,
      endereco: {
        cep: this.formulario.value.cep,
        logradouro: this.formulario.value.logradouro,
        numero: this.formulario.value.numero,
        complemento: this.formulario.value.complemento,
        bairro: this.formulario.value.bairro,
        cidade: {
          id: this.formulario.value.cidade
        }
      },
      foto: {
        id: this.imagem
      }
    } as ClienteModel;
    if (dados.id) {
      this.repository.putCliente(dados).subscribe(resposta => {
        this.messageService.add(
          {
            key: 'toast',
            severity: 'success',
            summary: 'CLIENTE',
            detail: 'atualizado com sucesso!'
          });
        this.limparFormulario();
      },
        (e) => {
          var msg: any[] = [];
          //Erro Principal
          msg.push({
            severity: 'error',
            summary: 'ERRO',
            detail: e.error.userMessage
          });
          //Erro de cada atributo
          var erros = e.error.objects;
          erros.forEach(function (elemento) {
            msg.push(
              {
                severity: 'error',
                summary: 'ERRO',
                detail: elemento.userMessage
              });
          });
          this.messageService.addAll(msg);
        });
    } else {
      this.repository.postCliente(dados).subscribe(resposta => {
        this.messageService.add(
          {
            key: 'toast',
            severity: 'success',
            summary: 'CLIENTE',
            detail: 'cadastrado com sucesso!'
          });
        //window.scrollTo(0, 0);
        this.limparFormulario();
      },
        (e) => {
          var msg: any[] = [];
          //Erro Principal
          msg.push({
            severity: 'error',
            summary: 'ERRO',
            detail: e.error.userMessage
          });
          //Erro de cada atributo
          var erros = e.error.objects;
          erros.forEach(function (elemento) {
            msg.push(
              {
                severity: 'error',
                summary: 'ERRO',
                detail: elemento.userMessage
              });
          });
          this.messageService.addAll(msg);
        }
      );
    }
  }
  listarEstados() {
    this.repository.getAllEstados().subscribe(resposta => {
      this.estados.push({ label: resposta.nome, value: resposta.id });
    });
  }
  listarCidades() {
    this.cidades = [];
    let id: number = this.formulario.value.estado;
    this.repository.getAllCidadesByEstado(id).subscribe(resposta => {
      this.cidades.push({ label: resposta.nome, value: resposta.id });
    });
  }
  listarCidadeSelecionada(idCidade: number) {
    this.cidades = [];
    let id: number = this.formulario.value.estado;
    this.repository.getAllCidadesByEstado(id).subscribe(resposta => {
      this.cidades.push({ label: resposta.nome, value: resposta.id });
      this.formulario.controls.cidade.setValue(idCidade);
    });
  }
  limparFormulario() {
    this.submitted = false;
    this.formulario.reset();
    this.cidades = [];
    this.estados = [];
    this.listarEstados();
    (this.upload as any).clear();
  }
  enviarImagem(evento) {
    this.uploadedFiles = [];
    for (let file of evento.files) {
      this.uploadedFiles.push(file);
    }
  }
}