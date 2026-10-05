package br.com.unipds.javify.catalogo.controller;

import br.com.unipds.javify.catalogo.domain.Album;
import br.com.unipds.javify.catalogo.domain.PagedAlbumList;
import br.com.unipds.javify.catalogo.service.AlbumService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.data.page.Page;

@Path("/api/v1/albuns")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlbumResource {

    @Inject
    AlbumService albumService;

    @GET
    public Response listaTodos(
            @QueryParam("titulo") String titulo,
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("20") int size) {

       PagedAlbumList resultado;
        if (titulo != null && !titulo.isBlank()) {
            resultado = albumService.buscaPorTitulo(titulo, page, size);
        } else {
            resultado = albumService.listarTodos(page, size);
        }

        return Response.ok(resultado).build();
    }

    @GET
    @Path("/{id}")
    public Response detalhaAlbum(@PathParam("id") String id) {
        Album album = albumService.buscarPorId(id);
        return Response.ok(album).build();
    }
}