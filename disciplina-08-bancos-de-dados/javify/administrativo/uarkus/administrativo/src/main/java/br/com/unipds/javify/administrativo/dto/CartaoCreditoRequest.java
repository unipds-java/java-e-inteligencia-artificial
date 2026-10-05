package br.com.unipds.javify.administrativo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CartaoCreditoRequest(
        @NotNull Integer assinaturaId,
        @Size(max = 100) String nomeTitular,
        @Size(max = 4) String ultimosQuatroDigitos,
        String tokenGateway,
        LocalDate validade
) {
}
