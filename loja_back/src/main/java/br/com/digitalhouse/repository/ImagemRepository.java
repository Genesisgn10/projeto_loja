package br.com.digitalhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digitalhouse.model.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long>{

}
