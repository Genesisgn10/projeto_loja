import { ClienteModel } from './../model/cliente-model';
import { ClienteEntity } from './../entity/cliente-entity';
import { Mapper } from '../../../app/base/mapper';

export class ClienteMapper extends Mapper<ClienteEntity, ClienteModel> {

    mapFrom(param: ClienteEntity): ClienteModel {
        return {
            id: param.id,
            nome: param.nome ? param.nome : '',
            sobrenome: param.sobrenome,
            telefones: param.telefones,
            dataNasc: param.dataNasc,
            cpf: param.cpf,
            rg: param.rg,
            email: param.email,
            endereco: param.endereco,
            foto: param.foto
        };
    }

    mapTo(param: ClienteModel): ClienteEntity {
        return {
            id: param.id,
            nome: param.nome,
            sobrenome: param.sobrenome,
            telefones: param.telefones,
            dataNasc: param.dataNasc,
            cpf: param.cpf,
            rg: param.rg,
            email: param.email,
            endereco: param.endereco,
            foto: param.foto
        };
    }
}
