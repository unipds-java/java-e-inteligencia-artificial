package br.com.unipds.javify.administrativo.controller;

import br.com.unipds.javify.administrativo.dto.EnderecoRequest;
import br.com.unipds.javify.administrativo.dto.EnderecoResponse;
import br.com.unipds.javify.administrativo.service.EnderecoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Path("/api/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Context
    UriInfo uriInfo;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GET
    public List<EnderecoResponse> listar() {
        return enderecoService.listarTodos();
    }

    @GET
    @Path("/{codigoPostal}")
    public EnderecoResponse buscar(@PathParam("codigoPostal") String codigoPostal) {
        return enderecoService.buscarPorCodigoPostal(codigoPostal);
    }

    @POST
    public Response criar(@Valid EnderecoRequest request) {
        var response = enderecoService.criar(request);
        URI location = uriInfo.getAbsolutePathBuilder()
                .path("{codigoPostal}")
                .build(response.codigoPostal());
        return Response.created(location).entity(response).build();
    }

    @PUT
    @Path("/{codigoPostal}")
    public EnderecoResponse atualizar(@PathParam("codigoPostal") String codigoPostal,
                                      @Valid EnderecoRequest request) {
        return enderecoService.atualizar(codigoPostal, request);
    }

    @DELETE
    @Path("/{codigoPostal}")
    public Response remover(@PathParam("codigoPostal") String codigoPostal) {
        enderecoService.remover(codigoPostal);
        return Response.noContent().build();
    }
}
