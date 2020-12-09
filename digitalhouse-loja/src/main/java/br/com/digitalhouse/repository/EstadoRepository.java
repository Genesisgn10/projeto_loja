package br.com.digitalhouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.digitalhouse.model.Cidade;
import br.com.digitalhouse.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	@Query("from Cidade where estado.id = :id")
	List<Cidade> buscarCidades(Long id);

}
