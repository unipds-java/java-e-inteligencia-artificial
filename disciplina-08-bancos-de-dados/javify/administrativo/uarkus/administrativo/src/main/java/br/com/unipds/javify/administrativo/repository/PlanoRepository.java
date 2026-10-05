package br.com.unipds.javify.administrativo.repository;

import br.com.unipds.javify.administrativo.domain.Plano;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.List;

@Repository
public interface PlanoRepository extends CrudRepository<Plano, Integer> {

    @Query("select p from Plano p order by p.id")
    List<Plano> listarTodos();
}
