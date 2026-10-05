package br.com.unipds.javify.administrativo.repository;

import br.com.unipds.javify.administrativo.domain.UsuarioTelefone;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioTelefoneRepository extends CrudRepository<UsuarioTelefone, Integer> {

    @Query("select t from UsuarioTelefone t join fetch t.usuario order by t.id")
    List<UsuarioTelefone> listarTodos();

    @Query("select t from UsuarioTelefone t join fetch t.usuario where t.id = :id")
    Optional<UsuarioTelefone> buscarPorId(Integer id);

    @Query("select t from UsuarioTelefone t join fetch t.usuario " +
            "where t.usuario.id = :usuarioId order by t.id")
    List<UsuarioTelefone> findByUsuarioId(Long usuarioId);
}
