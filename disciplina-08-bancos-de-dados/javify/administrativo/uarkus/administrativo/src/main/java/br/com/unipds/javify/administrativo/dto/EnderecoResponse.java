package br.com.unipds.javify.administrativo.dto;

public record EnderecoResponse(
        String codigoPostal,
        String logradouro,
        String bairro
) {
}
