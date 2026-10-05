package br.com.unipds.javify.administrativo.controller;

import br.com.unipds.javify.administrativo.dto.PlanoRequest;
import br.com.unipds.javify.administrativo.dto.PlanoResponse;
import br.com.unipds.javify.administrativo.service.PlanoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Path("/api/planos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlanoController {

    private final PlanoService planoService;

    @Context
    UriInfo uriInfo;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @GET
    public List<PlanoResponse> listar() {
        return planoService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public PlanoResponse buscar(@PathParam("id") Integer id) {
        return planoService.buscarPorId(id);
    }

    @POST
    public Response criar(@Valid PlanoRequest request) {
        var response = planoService.criar(request);
        URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .build(response.id());
        return Response.created(location).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    public PlanoResponse atualizar(@PathParam("id") Integer id, @Valid PlanoRequest request) {
        return planoService.atualizar(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Integer id) {
        planoService.remover(id);
        return Response.noContent().build();
    }
}
