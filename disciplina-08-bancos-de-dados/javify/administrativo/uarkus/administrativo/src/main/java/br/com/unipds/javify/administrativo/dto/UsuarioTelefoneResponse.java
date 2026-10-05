package br.com.unipds.javify.administrativo.dto;

public record UsuarioTelefoneResponse(
        Integer id,
        Long usuarioId,
        String numero,
        String tipo
) {
}
