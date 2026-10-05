package br.com.unipds.javify.catalogo.service;

import br.com.unipds.javify.catalogo.domain.Album;
import br.com.unipds.javify.catalogo.domain.PagedAlbumList;
import br.com.unipds.javify.catalogo.exception.AlbumNaoEncontradoException;
import br.com.unipds.javify.catalogo.repository.AlbumRepository;
import com.mongodb.client.model.Filters;
import io.quarkus.cache.*;
import io.quarkus.logging.Log;
import jakarta.data.Order;
import jakarta.data.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import org.bson.conversions.Bson;
import org.eclipse.jnosql.databases.mongodb.mapping.MongoDBTemplate;

import java.util.List;

@ApplicationScoped
public class AlbumService {

    @Inject
    AlbumRepository albumRepository;

    @Inject
    MongoDBTemplate mongoDBTemplate;

    @Inject
    CacheManager cacheManager;

    @CacheResult(cacheName = "albuns")
    public PagedAlbumList listarTodos(int page, int size) {
        Log.info("Chamando AlbumService.listarTodos");
        PageRequest pageRequest = PageRequest.ofPage(page).size(size);
        Page<Album> albumPage = albumRepository.findAll(pageRequest, Order.by(Sort.desc("anoLancamento")));
        long totalElements = mongoDBTemplate.count(Album.class);
        return new PagedAlbumList(albumPage.content(), totalElements, page, size);
    }

    public PagedAlbumList buscaPorTitulo(String titulo, int page, int size) {
        Bson regexFilter = Filters.regex("titulo", titulo, "i");
        List<Album> albuns = mongoDBTemplate.select(Album.class, regexFilter)
                .skip((long) (page - 1) * size)
                .limit(size)
                .toList();
        long totalElements = mongoDBTemplate.count(Album.class);
        return new PagedAlbumList(albuns, totalElements, page, size);
    }

    @CacheResult(cacheName = "album")
    public Album buscarPorId(@CacheKey String id) {
        Log.info("Chamando AlbumService.buscarPorId");
        return albumRepository.findById(id)
                .orElseThrow(() -> new AlbumNaoEncontradoException(id));
    }

    @CacheInvalidateAll(cacheName = "albuns")
    public Album salvarNovoAlbum(Album novoAlbum) {
        return albumRepository.save(novoAlbum);
    }

//    @CacheInvalidate(cacheName = "album")
    @CacheInvalidateAll(cacheName = "albuns")
    public Album atualizarAlbum(/* @CacheKey */ String id, Album albumAtualizado) {
        buscarPorId(id);
        if (!id.equals(albumAtualizado.id())) {
            throw new AlbumNaoEncontradoException(albumAtualizado.id());
        }
        Album salvo = albumRepository.save(albumAtualizado);
        cacheManager.getCache("album").ifPresent(cache -> cache.invalidate(id).await().indefinitely());
        return salvo;
    }

    @CacheInvalidate(cacheName = "album")
    @CacheInvalidateAll(cacheName = "albuns")
    public void excluirAlbum(@CacheKey String id) {
        System.out.println("Vai excluir o album de id: " + id);
        if (!albumRepository.existsById(id)) {
            System.out.println("Nao encontrou o album de id: " + id);
            throw new AlbumNaoEncontradoException(id);
        }
        albumRepository.deleteById(id);
    }
}
