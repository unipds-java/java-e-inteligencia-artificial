package br.com.unipds.javify.administrativo.dto;

import br.com.unipds.javify.administrativo.domain.TipoTelefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioTelefoneRequest(
        @NotNull Long usuarioId,
        @NotBlank String numero,
        @NotNull TipoTelefone tipo
) {
}
