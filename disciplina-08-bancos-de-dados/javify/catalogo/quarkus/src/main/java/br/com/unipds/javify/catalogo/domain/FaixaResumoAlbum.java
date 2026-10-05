package br.com.unipds.javify.catalogo.domain;

import jakarta.nosql.Column;
import jakarta.nosql.Embeddable;
import jakarta.nosql.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record FaixaResumoAlbum(
        @Id
        String id,

        @Column
        @NotNull(message = "O número da faixa é obrigatório.")
        @Min(value = 1, message = "O número da faixa deve ser maior que zero.")
        Integer numero,

        @Column
        @NotBlank(message = "O título da faixa não pode estar em branco.")
        String titulo,

        @Column
        @NotNull(message = "A duração da faixa é obrigatória.")
        @Min(value = 1, message = "A duração deve ter pelo menos 1 segundo.")
        Integer duracaoSegundos ) {}

