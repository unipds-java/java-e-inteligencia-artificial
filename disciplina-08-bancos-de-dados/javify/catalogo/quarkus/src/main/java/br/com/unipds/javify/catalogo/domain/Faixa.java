package br.com.unipds.javify.catalogo.domain;


import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.List;

@Entity("faixas")
public record Faixa (

        @Id String id,

        @Column
        String titulo,

        @Column
        Integer duracaoSegundos,

        @Column
        String arquivoAudioUrl,

        @Column
        String isrc,

        @Column
        Long totalReproducoes,

        @Column
        Boolean conteudoExplicito,

        @Column
        String letraUrl,

        @Column
        Integer bpm,

        @Column
        Double energia,

        @Column
        Boolean instrumental,

        @Column
        AlbumResumoFaixa album,

        @Column("artistas")
        List<ArtistaParticipante> artistas

){
}
