package br.com.unipds.javify.administrativo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PlanoRequest(
        @NotBlank @Size(max = 50) String nome,
        @NotNull @Positive BigDecimal preco,
        @NotNull Boolean possuiPropagandas,
        @NotNull @Positive Integer limiteMembros,
        @NotNull Boolean modoOffline
) {
}
