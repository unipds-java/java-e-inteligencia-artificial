package br.com.unipds.javify.administrativo.dto;

import java.math.BigDecimal;

public record PlanoResponse(
        Integer id,
        String nome,
        BigDecimal preco,
        boolean possuiPropagandas,
        int limiteMembros,
        boolean modoOffline
) {
}
