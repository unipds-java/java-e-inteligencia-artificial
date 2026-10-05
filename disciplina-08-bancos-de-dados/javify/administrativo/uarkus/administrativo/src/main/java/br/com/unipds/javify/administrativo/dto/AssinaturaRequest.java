package br.com.unipds.javify.administrativo.dto;

import jakarta.validation.constraints.NotNull;

public record AssinaturaRequest(
        @NotNull Integer planoId,
        @NotNull Boolean statusAtiva
) {
}
