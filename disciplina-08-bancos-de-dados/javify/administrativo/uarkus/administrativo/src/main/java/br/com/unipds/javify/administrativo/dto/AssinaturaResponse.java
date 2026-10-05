package br.com.unipds.javify.administrativo.dto;

public record AssinaturaResponse(
        Integer id,
        PlanoResponse plano,
        boolean statusAtiva
) {
}
