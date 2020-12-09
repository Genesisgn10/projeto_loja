package br.com.digitalhouse.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name="cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column
	private String nome;	
	
	@Column
	private String sobrenome;		
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Telefone> telefones;
	
	@DateTimeFormat(pattern="yyyy-mm-dd")
	@Column(name="data_nasc")
	private LocalDate dataNasc;		
	
	@Column
	private String cpf;		

	@Column
	private String rg;	

	@Column
	private String email;
	
	@Embedded
	private Endereco endereco;
	
	@OneToOne
	private Imagem foto;
	
}
