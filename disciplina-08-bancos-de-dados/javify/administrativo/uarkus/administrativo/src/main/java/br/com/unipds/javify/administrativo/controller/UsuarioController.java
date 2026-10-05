package br.com.unipds.javify.administrativo.controller;

import br.com.unipds.javify.administrativo.dto.UsuarioRequest;
import br.com.unipds.javify.administrativo.dto.UsuarioResponse;
import br.com.unipds.javify.administrativo.dto.UsuarioTelefoneRequest;
import br.com.unipds.javify.administrativo.dto.UsuarioTelefoneResponse;
import br.com.unipds.javify.administrativo.service.UsuarioService;
import br.com.unipds.javify.administrativo.service.UsuarioTelefoneService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Path("/api/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioTelefoneService usuarioTelefoneService;

    @Context
    UriInfo uriInfo;

    public UsuarioController(UsuarioService usuarioService,
                             UsuarioTelefoneService usuarioTelefoneService) {
        this.usuarioService = usuarioService;
        this.usuarioTelefoneService = usuarioTelefoneService;
    }

    @GET
    public List<UsuarioResponse> listar() {
        return usuarioService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public UsuarioResponse buscar(@PathParam("id") Long id) {
        return usuarioService.buscarPorId(id);
    }

    @POST
    public Response criar(@Valid UsuarioRequest request) {
        var response = usuarioService.criar(request);
        URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .build(response.id());
        return Response.created(location).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    public UsuarioResponse atualizar(@PathParam("id") Long id, @Valid UsuarioRequest request) {
        return usuarioService.atualizar(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        usuarioService.remover(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{usuarioId}/telefones")
    public List<UsuarioTelefoneResponse> listarTelefones(@PathParam("usuarioId") Long usuarioId) {
        return usuarioTelefoneService.listarPorUsuario(usuarioId);
    }

    @POST
    @Path("/{usuarioId}/telefones")
    public Response criarTelefone(@PathParam("usuarioId") Long usuarioId,
                                  @Valid UsuarioTelefoneRequest request) {
        var response = usuarioTelefoneService.criar(usuarioId, request);
        URI location = uriInfo.getBaseUriBuilder()
                .path("/api/telefones/{id}")
                .build(response.id());
        return Response.created(location).entity(response).build();
    }
}
