package br.com.unipds.javify.catalogo.domain;

import java.util.List;

public record PagedAlbumList (
        List<Album> content,
        long totalElements,
        int page,
        int size) {
}
