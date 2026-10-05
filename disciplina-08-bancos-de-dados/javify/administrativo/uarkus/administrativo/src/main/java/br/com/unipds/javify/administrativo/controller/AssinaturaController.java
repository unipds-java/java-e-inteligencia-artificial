package br.com.unipds.javify.administrativo.controller;

import br.com.unipds.javify.administrativo.dto.AssinaturaRequest;
import br.com.unipds.javify.administrativo.dto.AssinaturaResponse;
import br.com.unipds.javify.administrativo.dto.CartaoCreditoRequest;
import br.com.unipds.javify.administrativo.dto.CartaoCreditoResponse;
import br.com.unipds.javify.administrativo.service.AssinaturaService;
import br.com.unipds.javify.administrativo.service.CartaoCreditoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Path("/api/assinaturas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AssinaturaController {

    private final AssinaturaService assinaturaService;
    private final CartaoCreditoService cartaoCreditoService;

    @Context
    UriInfo uriInfo;

    public AssinaturaController(AssinaturaService assinaturaService,
                                CartaoCreditoService cartaoCreditoService) {
        this.assinaturaService = assinaturaService;
        this.cartaoCreditoService = cartaoCreditoService;
    }

    @GET
    public List<AssinaturaResponse> listar() {
        return assinaturaService.listarTodas();
    }

    @GET
    @Path("/{id}")
    public AssinaturaResponse buscar(@PathParam("id") Integer id) {
        return assinaturaService.buscarPorId(id);
    }

    @POST
    public Response criar(@Valid AssinaturaRequest request) {
        var response = assinaturaService.criar(request);
        URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .build(response.id());
        return Response.created(location).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    public AssinaturaResponse atualizar(@PathParam("id") Integer id, @Valid AssinaturaRequest request) {
        return assinaturaService.atualizar(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Integer id) {
        assinaturaService.remover(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{assinaturaId}/cartoes")
    public List<CartaoCreditoResponse> listarCartoes(@PathParam("assinaturaId") Integer assinaturaId) {
        return cartaoCreditoService.listarPorAssinatura(assinaturaId);
    }

    @POST
    @Path("/{assinaturaId}/cartoes")
    public Response criarCartao(@PathParam("assinaturaId") Integer assinaturaId,
                                @Valid CartaoCreditoRequest request) {
        var response = cartaoCreditoService.criar(assinaturaId, request);
        URI location = uriInfo.getBaseUriBuilder()
                .path("/api/cartoes/{id}")
                .build(response.id());
        return Response.created(location).entity(response).build();
    }
}
