package br.com.unipds.javify.administrativo.controller;

import br.com.unipds.javify.administrativo.dto.UsuarioTelefoneRequest;
import br.com.unipds.javify.administrativo.dto.UsuarioTelefoneResponse;
import br.com.unipds.javify.administrativo.service.UsuarioTelefoneService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

/**
 * As rotas aninhadas em /api/usuarios/{id}/telefones ficam no UsuarioController:
 * em JAX-RS o root resource e escolhido pelo prefixo do @Path, sem backtracking.
 */
@Path("/api/telefones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioTelefoneController {

    private final UsuarioTelefoneService usuarioTelefoneService;

    @Context
    UriInfo uriInfo;

    public UsuarioTelefoneController(UsuarioTelefoneService usuarioTelefoneService) {
        this.usuarioTelefoneService = usuarioTelefoneService;
    }

    @GET
    public List<UsuarioTelefoneResponse> listar() {
        return usuarioTelefoneService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public UsuarioTelefoneResponse buscar(@PathParam("id") Integer id) {
        return usuarioTelefoneService.buscarPorId(id);
    }

    @POST
    public Response criar(@Valid UsuarioTelefoneRequest request) {
        var response = usuarioTelefoneService.criar(request);
        URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .build(response.id());
        return Response.created(location).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    public UsuarioTelefoneResponse atualizar(@PathParam("id") Integer id, @Valid UsuarioTelefoneRequest request) {
        return usuarioTelefoneService.atualizar(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Integer id) {
        usuarioTelefoneService.remover(id);
        return Response.noContent().build();
    }
}
