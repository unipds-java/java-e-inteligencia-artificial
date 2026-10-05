package br.com.unipds.javify.administrativo.repository;

import br.com.unipds.javify.administrativo.domain.CartaoCredito;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartaoCreditoRepository extends CrudRepository<CartaoCredito, Integer> {

    @Query("select c from CartaoCredito c join fetch c.assinatura order by c.id")
    List<CartaoCredito> listarTodos();

    @Query("select c from CartaoCredito c join fetch c.assinatura where c.id = :id")
    Optional<CartaoCredito> buscarPorId(Integer id);

    @Query("select c from CartaoCredito c join fetch c.assinatura " +
            "where c.assinatura.id = :assinaturaId order by c.id")
    List<CartaoCredito> findByAssinaturaId(Integer assinaturaId);
}
