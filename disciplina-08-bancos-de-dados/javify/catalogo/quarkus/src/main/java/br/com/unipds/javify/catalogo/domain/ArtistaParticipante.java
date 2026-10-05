package br.com.unipds.javify.catalogo.domain;

import jakarta.nosql.Column;
import jakarta.nosql.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record ArtistaParticipante(
        @Column
        @NotBlank(message = "O nome do artista é obrigatório.")
        String nome,

        @Column
        @NotBlank(message = "A função do artista é obrigatória.")
        String funcao ) {}

