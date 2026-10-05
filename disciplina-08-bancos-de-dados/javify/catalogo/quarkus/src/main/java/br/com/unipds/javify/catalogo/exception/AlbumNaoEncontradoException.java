package br.com.unipds.javify.catalogo.exception;

public class AlbumNaoEncontradoException extends RuntimeException {
    public AlbumNaoEncontradoException(String id) {
        super("Álbum com ID '" + id + "' não encontrado no catálogo.");
    }
}
