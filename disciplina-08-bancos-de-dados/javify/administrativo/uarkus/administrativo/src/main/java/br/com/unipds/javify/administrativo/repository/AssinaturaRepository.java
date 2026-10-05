package br.com.unipds.javify.administrativo.repository;

import br.com.unipds.javify.administrativo.domain.Assinatura;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssinaturaRepository extends CrudRepository<Assinatura, Integer> {

    @Query("select a from Assinatura a " +
            "join fetch a.plano " +
            "order by a.id")
    List<Assinatura> buscarAssinaturasComPlano();

    @Query("select a from Assinatura a " +
            "join fetch a.plano " +
            "where a.id = :id")
    Optional<Assinatura> buscarComPlanoPorId(Integer id);

    @Query("select a from Assinatura a " +
            "join fetch a.plano " +
            "left join fetch a.cartoes " +
            "where a.id = :id")
    Optional<Assinatura> buscarComCartoes(Integer id);

    // Em Jakarta Data as operacoes nunca sao cascateadas (a sessao e stateless),
    // entao os cartoes precisam ser removidos explicitamente.
    @Query("delete from CartaoCredito c where c.assinatura.id = :assinaturaId")
    void removerCartoesDaAssinatura(Integer assinaturaId);
}
