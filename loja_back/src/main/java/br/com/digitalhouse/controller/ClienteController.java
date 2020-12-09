package br.com.digitalhouse.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalhouse.controller.openapi.ClienteControllerOpenAPI;
import br.com.digitalhouse.dto.ClienteDTO;
import br.com.digitalhouse.dto.ClienteResumoDTO;
import br.com.digitalhouse.model.Cliente;
import br.com.digitalhouse.model.Telefone;
import br.com.digitalhouse.request.ClienteRequest;
import br.com.digitalhouse.security.permissoes.CheckSecurity;
import br.com.digitalhouse.service.ClienteService;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteController implements ClienteControllerOpenAPI {

	@Autowired
	private ClienteService service;

	@Override
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody ClienteRequest clienteRequest) {
		try {
			ClienteDTO clienteDTO = service.salvar(clienteRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@Override
	@GetMapping("/resumo")
	public List<ClienteResumoDTO> listarResumo() {
		return service.listarResumo();
	}

	@CheckSecurity.Cliente.PodeConsultar
	@Override
	@GetMapping
	public List<ClienteDTO> listar() {
		return service.listar();
	}


	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {

		Optional<Cliente> cliente = service.buscar(id);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}

		return ResponseEntity.notFound().build();

	}

	@Override
	@GetMapping("/{id}/telefones")
	public List<Telefone> buscarTelefones(@PathVariable Long id) {
		return service.buscarTelefones(id);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> excluir(@PathVariable Long id) {
		try {
			service.excluir(id);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Cliente cliente, @PathVariable Long id) {

		Cliente clienteAtual = service.buscar(id).orElse(null);

		if (clienteAtual != null) {
			BeanUtils.copyProperties(cliente, clienteAtual, "id");

			service.atualizar(clienteAtual);
			return ResponseEntity.ok(clienteAtual);
		}

		return ResponseEntity.notFound().build();
	}

}
