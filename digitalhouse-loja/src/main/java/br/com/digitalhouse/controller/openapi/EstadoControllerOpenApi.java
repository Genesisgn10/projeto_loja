package br.com.digitalhouse.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import br.com.digitalhouse.model.Cidade;
import br.com.digitalhouse.model.Estado;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Estado")
public interface EstadoControllerOpenApi {

	@ApiOperation("Cadastrar um estado")
	@ApiResponses({ @ApiResponse(code = 201, message = "Estado cadastrado", response = Estado.class) })	
	void salvar(
			@ApiParam(name = "corpo", value = "Representação de um novo estado", required = true) 
			@Valid Estado estado);
	
	@ApiOperation(value = "Buscar todos os Estados", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Buscar todos os Estados", response = Estado.class) })
	List<Estado> listar();
	
	@ApiOperation(value = "Buscar todas as Cidades por Estado", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Buscar todas as Cidades por Estado", response = Cidade.class) })
	@ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
	List<Cidade> listarCidadesPorEstado(Long id);
	
}
