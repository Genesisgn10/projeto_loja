package br.com.digitalhouse.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.digitalhouse.dto.ClienteDTO;
import br.com.digitalhouse.dto.ClienteResumoDTO;
import br.com.digitalhouse.exception.config.Problem;
import br.com.digitalhouse.model.Cliente;
import br.com.digitalhouse.model.Telefone;
import br.com.digitalhouse.request.ClienteRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Cliente")
public interface ClienteControllerOpenAPI {

	
	@ApiOperation("Cadastrar um cliente")
	@ApiResponses({ @ApiResponse(code = 201, message = "Cliente cadastrado", response = ClienteDTO.class) })	
	ResponseEntity<?> salvar(
			@ApiParam(name = "corpo", value = "Representação de um novo cliente", required = true) 
			@Valid ClienteRequest clienteRequest);

	
	@ApiOperation(value = "Buscar todos os Clientes resumido", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Buscar todos os clientes resumido", response = ClienteResumoDTO.class) })
	List<ClienteResumoDTO> listarResumo();
	

	@ApiOperation(value = "Buscar todos os Clientes", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Buscar todos os clientes", response = ClienteDTO.class) })
	List<ClienteDTO> listar();
	

	@ApiOperation(value = "Buscar Cliente pelo ID", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Buscar Cliente pelo ID", response = ClienteDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<Cliente> buscar(Long id);
	

	@ApiOperation(value = "Buscar Telefones do Cliente pelo ID", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Buscar Telefones do Cliente pelo ID", response = Telefone.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "ID do Cliente a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
	List<Telefone> buscarTelefones(Long id);
	

	@ApiOperation(value = "Excluir Cliente pelo ID", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 204, message = "Cliente excluído com sucesso", response = ClienteDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser excluído", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<Cliente> excluir(Long id);
	

	@ApiOperation(value = "Atualizar Cliente pelo ID", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Cliente atualizado com sucesso.", response = ClienteDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser atualizado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<?> atualizar(
			@ApiParam(name = "corpo", value = "Representação de um novo cliente", required = true) @Valid Cliente cliente,
			Long id);

}
