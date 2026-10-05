package br.com.unipds.filmes.repository;

import br.com.unipds.filmes.model.Ator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtorRepository extends JpaRepository<Ator, Integer> {

    List<Ator> findByNomeContainsIgnoreCaseOrderByNomeAsc(String nome);
}

