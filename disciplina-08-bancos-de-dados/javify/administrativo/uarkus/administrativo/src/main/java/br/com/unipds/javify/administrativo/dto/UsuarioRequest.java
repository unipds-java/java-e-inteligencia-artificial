package br.com.unipds.javify.administrativo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UsuarioRequest(
        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Email @Size(max = 150) String email,
        @NotBlank @Size(max = 60) String senhaHash,
        @NotNull Boolean titular,
        @Past LocalDate dataNascimento,
        @NotNull EnderecoRequest endereco,
        Integer planoId,
        Integer assinaturaId
) {
}
