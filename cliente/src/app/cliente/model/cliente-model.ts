export interface ClienteModel {
    id?: number;
    nome?: string;
    sobrenome?: string;
    telefones?: { id: number; telefone: string, tipo: string }[];
    dataNasc?: string;
    cpf?: string;
    rg?: string;
    email?: string;
    endereco?: EnderecoModel;
    foto?: any;
}

export interface EnderecoModel {
    cep?: string;
    logradouro?: string;
    numero?: string;
    complemento?: string;
    bairro?: string;
    cidade?: CidadeModel;
}

export interface CidadeModel {
    id?: number;
    nome?: string;
    estado?: EstadoModel;
}

export interface EstadoModel {
    id?: number;
    nome?: string;
}
