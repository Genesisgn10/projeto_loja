package br.com.digitalhouse.service;

import java.net.URL;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.digitalhouse.dto.ImagemDTO;
import br.com.digitalhouse.exception.ImagemNaoEncontradadException;
import br.com.digitalhouse.mapper.ImagemMapper;
import br.com.digitalhouse.model.Imagem;
import br.com.digitalhouse.repository.ImagemRepository;
import br.com.digitalhouse.request.ImagemRequest;

@Service
public class ImagemService {

	@Autowired
	private ImagemRepository repository;
	@Autowired
	private ImagemMapper mapper;	
	@Autowired
	private S3FotoStorageService s3FotoStorageService;
	
	@Transactional
	public ImagemDTO salvar(ImagemRequest imagemRequest) {
		
		MultipartFile arquivo = imagemRequest.getImagem();
		
		String nomeArquivo = UUID.randomUUID().toString() 
				+ "_" + arquivo.getOriginalFilename();	
		
		Imagem imagem = mapper.requestToModel(imagemRequest);
		
		imagem.setNomeArquivo(arquivo.getOriginalFilename());
		imagem.setNomeArquivoCompleto(nomeArquivo);		
		imagem.setContentType(arquivo.getContentType());
		imagem.setTamanho(arquivo.getSize());		
	  	
		URL url = s3FotoStorageService.armazenar(arquivo, nomeArquivo);
	  	imagem.setUrl(url);
	    
	  	return mapper.modelToDTO( repository.save(imagem) );	
	}

	@Transactional
	public void excluir(Long id) {
		
		Imagem imagem = repository.findById(id).get();
		
		s3FotoStorageService.remover(imagem.getNomeArquivoCompleto());
		
		try {
			repository.deleteById(id);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new ImagemNaoEncontradadException(id);
		};
		
	}
}
