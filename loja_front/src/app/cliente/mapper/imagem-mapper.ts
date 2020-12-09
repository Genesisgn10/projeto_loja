import { ImagemEntity } from './../entity/imagem-entity';
import { Mapper } from '../../../app/base/mapper';
import { ImagemModel } from '../model/imagem-model';

export class ImagemMapper extends Mapper<ImagemEntity, ImagemModel> {

  mapFrom(param: ImagemEntity): ImagemModel {

    return {
      id: param.id,
      nomeArquivo: param.nomeArquivo,
      contentType: param.contentType,
      tamanho: param.tamanho,
      url: param.url
    };
  }

  mapTo(param: ImagemModel): ImagemEntity {
    return {
      id: param.id,
      nomeArquivo: param.nomeArquivo,
      contentType: param.contentType,
      tamanho: param.tamanho,
      url: param.url
    };
  }
}
