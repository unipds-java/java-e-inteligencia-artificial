package br.com.unipds.javify.administrativo.dto;

import java.time.LocalDate;

public record CartaoCreditoResponse(
        Integer id,
        Integer assinaturaId,
        String nomeTitular,
        String ultimosQuatroDigitos,
        String tokenGateway,
        LocalDate validade
) {
}
