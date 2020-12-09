package br.com.digitalhouse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.digitalhouse.model.Endereco;
import lombok.Data;

@Data
public class ClienteResumoDTO {

	private Long id;
	private String nome;	
	private String sobrenome;	
	private String email;
	@JsonIgnoreProperties("cidade")
	private Endereco endereco;
}
