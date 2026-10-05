package br.com.unipds.filmes.repository;

import br.com.unipds.filmes.model.Filme;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {

    @Query("""
       select distinct f from Filme f
           left join fetch f.diretores
           left join fetch f.atuacoes a
           left join fetch a.id.ator
           where lower(f.titulo) like lower(concat('%', :titulo, '%'))
           order by f.titulo asc
       """)
    List<Filme> findByTituloContainsIgnoreCaseOrderByTituloAsc(String titulo);

//    @Query("""
//        select f from Filme f
//            left join fetch f.atuacoes a
//            left join fetch a.id.ator
//            left join fetch f.diretores
//            order by f.titulo
//        """)
//    List<Filme> obterFilmes();

    @Transactional
    default List<Filme> obterFilmes() {
        obterFilmesComAtuacoes();
        return obterFilmesComDiretores();
    }

    @Query("""
        select f from Filme f
            left join fetch f.atuacoes a
            left join fetch a.id.ator
            order by f.titulo
        """)
    List<Filme> obterFilmesComAtuacoes();


    @Query("""
            select f from Filme f
                left join fetch f.diretores
                order by f.titulo
            """)
    List<Filme> obterFilmesComDiretores();
}
