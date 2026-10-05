package br.com.unipds.javify.administrativo.controller;

import br.com.unipds.javify.administrativo.dto.CartaoCreditoRequest;
import br.com.unipds.javify.administrativo.dto.CartaoCreditoResponse;
import br.com.unipds.javify.administrativo.service.CartaoCreditoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

/**
 * As rotas aninhadas em /api/assinaturas/{id}/cartoes ficam no AssinaturaController:
 * em JAX-RS o root resource e escolhido pelo prefixo do @Path, sem backtracking.
 */
@Path("/api/cartoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartaoCreditoController {

    private final CartaoCreditoService cartaoCreditoService;

    @Context
    UriInfo uriInfo;

    public CartaoCreditoController(CartaoCreditoService cartaoCreditoService) {
        this.cartaoCreditoService = cartaoCreditoService;
    }

    @GET
    public List<CartaoCreditoResponse> listar() {
        return cartaoCreditoService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public CartaoCreditoResponse buscar(@PathParam("id") Integer id) {
        return cartaoCreditoService.buscarPorId(id);
    }

    @POST
    public Response criar(@Valid CartaoCreditoRequest request) {
        var response = cartaoCreditoService.criar(request);
        URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .build(response.id());
        return Response.created(location).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    public CartaoCreditoResponse atualizar(@PathParam("id") Integer id, @Valid CartaoCreditoRequest request) {
        return cartaoCreditoService.atualizar(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Integer id) {
        cartaoCreditoService.remover(id);
        return Response.noContent().build();
    }
}
