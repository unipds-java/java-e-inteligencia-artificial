package br.com.unipds.javify.catalogo.controller;

import br.com.unipds.javify.catalogo.domain.Album;
import br.com.unipds.javify.catalogo.service.AlbumService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/admin/albuns")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminAlbumResource {

    @Inject
    AlbumService albumService;

    @POST
    public Response criaAlbum(@Valid Album novoAlbum) {
        Album albumSalvo = albumService.salvarNovoAlbum(novoAlbum);
        return Response.status(Response.Status.CREATED).entity(albumSalvo).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizaAlbum(@PathParam("id") String id, @Valid Album albumAtualizado) {
        Album albumSalvo = albumService.atualizarAlbum(id, albumAtualizado);
        return Response.ok(albumSalvo).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluiAlbum(@PathParam("id") String id) {
        albumService.excluirAlbum(id);
        return Response.noContent().build();
    }
}
