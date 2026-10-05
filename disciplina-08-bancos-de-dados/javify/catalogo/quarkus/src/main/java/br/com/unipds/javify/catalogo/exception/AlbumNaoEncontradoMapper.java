package br.com.unipds.javify.catalogo.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public class AlbumNaoEncontradoMapper implements ExceptionMapper<AlbumNaoEncontradoException> {

    @Override
    public Response toResponse(AlbumNaoEncontradoException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Map.of("erro", exception.getMessage()))
                .build();
    }
}
