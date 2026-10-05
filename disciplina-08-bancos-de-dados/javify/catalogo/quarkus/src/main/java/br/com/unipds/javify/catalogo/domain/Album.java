package br.com.unipds.javify.catalogo.domain;

import jakarta.nosql.Column;
import jakarta.nosql.Convert;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.eclipse.jnosql.databases.mongodb.mapping.ObjectIdConverter;

import java.util.List;

@Entity("albuns")
public record Album(
        @Id
        @Convert(ObjectIdConverter.class)
        String id,

        @Column
        @NotBlank(message = "O título do álbum é obrigatório e não pode estar em branco.")
        String titulo,

        @Column
        @NotNull(message = "O ano de lançamento é obrigatório.")
        @Min(value = 1900, message = "O ano de lançamento deve ser maior que 1900.")
        Integer anoLancamento,

        @Column
        @NotBlank(message = "O gênero musical é obrigatório.")
        String genero,

        @Column
        String capaUrl,

        @Column("artistas")
        List<ArtistaParticipante> artistas,

        @Column("faixas")
        List<FaixaResumoAlbum> faixas
) {}