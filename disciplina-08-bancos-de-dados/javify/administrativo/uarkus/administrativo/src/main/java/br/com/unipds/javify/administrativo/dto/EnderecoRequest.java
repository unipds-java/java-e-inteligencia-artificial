package br.com.unipds.javify.administrativo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoRequest(
        @NotBlank @Size(max = 8) String codigoPostal,
        @Size(max = 150) String logradouro,
        @Size(max = 100) String bairro
) {
}
