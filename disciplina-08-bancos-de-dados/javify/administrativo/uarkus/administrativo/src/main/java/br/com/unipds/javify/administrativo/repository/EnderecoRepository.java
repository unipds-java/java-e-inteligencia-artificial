package br.com.unipds.javify.administrativo.repository;

import br.com.unipds.javify.administrativo.domain.Endereco;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {

    @Query("select e from Endereco e order by e.codigoPostal")
    List<Endereco> listarTodos();

    @Find
    Optional<Endereco> findByCodigoPostal(String codigoPostal);
}
