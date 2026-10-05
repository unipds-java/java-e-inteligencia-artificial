package br.com.unipds.javify.administrativo.dto;

import java.time.LocalDate;
import java.util.List;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        boolean titular,
        LocalDate dataNascimento,
        EnderecoResponse endereco,
        AssinaturaResponse assinatura,
        List<UsuarioTelefoneResponse> telefones
) {
}
