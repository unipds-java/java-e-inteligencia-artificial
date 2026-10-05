package br.com.unipds.javify.catalogo.repository;

import br.com.unipds.javify.catalogo.domain.Album;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.Repository;
import org.eclipse.jnosql.mapping.NoSQLRepository;

@Repository
public interface AlbumRepository extends NoSQLRepository<Album, String> {

    // funciona mas nao ignora case
    Page<Album> findByTituloContains(String titulo, PageRequest pageRequest);

    // ERRO:  java.lang.UnsupportedOperationException: The condition IGNORE_CASE is not supported from mongoDB Driver
    Page<Album> findByTituloIgnoreCaseContains(String titulo, PageRequest pageRequest);

}