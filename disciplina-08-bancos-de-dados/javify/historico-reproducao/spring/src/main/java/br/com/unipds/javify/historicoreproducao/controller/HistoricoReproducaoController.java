package br.com.unipds.javify.historicoreproducao.controller;

import br.com.unipds.javify.historicoreproducao.domain.HistoricoReproducao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analytics/historico")
public class HistoricoReproducaoController {

    @PostMapping("/{usuarioId}/tocar/{faixaId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void registrarPlay(
            @PathVariable Long usuarioId,
            @PathVariable String faixaId,
            @RequestParam String nomeFaixa) {

        throw new UnsupportedOperationException("TODO");

    }

    @GetMapping("/{usuarioId}")
    public List<HistoricoReproducao> obterHistoricoUsuario(@PathVariable Long usuarioId) {

        throw new UnsupportedOperationException("TODO");

    }

}