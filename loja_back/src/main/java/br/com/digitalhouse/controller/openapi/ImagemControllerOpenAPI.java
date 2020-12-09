package br.com.digitalhouse.controller.openapi;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.digitalhouse.dto.ImagemDTO;
import br.com.digitalhouse.exception.config.Problem;
import br.com.digitalhouse.request.ImagemRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Imagem")
public interface ImagemControllerOpenAPI {

	
	@ApiOperation("Cadastrar uma imagem")
	@ApiResponses({ @ApiResponse(code = 201, message = "Imagem cadastrada", response = ImagemDTO.class) })	
	ImagemDTO salvarFoto(
			@ApiParam(name = "corpo", value = "Representação de uma nova imagem", required = true) 
			@Valid ImagemRequest imagem);
	
	@ApiOperation(value = "Excluir Imagem pelo ID", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 204, message = "Imagem excluída com sucesso", response = ImagemDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser excluído", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<ImagemDTO> excluir(@PathVariable Long id);
}
