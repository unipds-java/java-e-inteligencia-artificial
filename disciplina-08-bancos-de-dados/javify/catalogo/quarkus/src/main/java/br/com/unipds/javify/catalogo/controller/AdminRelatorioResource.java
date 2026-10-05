package br.com.unipds.javify.catalogo.controller;

import br.com.unipds.javify.catalogo.service.RelatorioAlbumService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/admin/relatorios")
@Produces(MediaType.APPLICATION_JSON)
public class AdminRelatorioResource {

    @Inject
    RelatorioAlbumService relatorioAlbumService;

    @GET
    @Path("duracao-genero")
    public Response obterRelatorioDuracaoPorGenero(@QueryParam("genero") String genero) {

        var resultado = relatorioAlbumService.calcularDuracaoTotalPorGenero(genero);

        if (resultado == null) {
            resultado = new RelatorioAlbumService.ResultadoRelatorioDuracaoGenero(0L, 0L);
        }

        return Response.ok(resultado).build();
    }
}