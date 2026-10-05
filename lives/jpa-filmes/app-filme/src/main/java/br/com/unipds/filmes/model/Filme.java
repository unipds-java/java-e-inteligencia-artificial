package br.com.unipds.filmes.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "movie")
public class Filme {

    @Id
    @Column(name = "mov_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_seq")
    @SequenceGenerator(name = "movie_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "mov_title")
    private String titulo;

    @Column(name = "mov_year")
    private Integer ano;

    @Column(name = "mov_time")
    private Integer duracao;

    @Column(name = "mov_lang")
    private String idioma;

    @Column(name = "mov_dt_rel")
    private LocalDate dataLancamento;

    @Column(name = "mov_rel_country")
    private String pais;


    @OneToMany(mappedBy = "id.filme", fetch = FetchType.LAZY)
    private Set<Atuacao> atuacoes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_direction",
            joinColumns = @JoinColumn(name = "mov_id"),
            inverseJoinColumns = @JoinColumn(name = "dir_id")
    )
    private Set<Diretor> diretores = new HashSet<>();

    public Set<Atuacao> getAtuacoes() {
        return atuacoes;
    }
    public void setAtuacoes(Set<Atuacao> atuacoes) {
        this.atuacoes = atuacoes;
    }
    public Set<Diretor> getDiretores() {
        return diretores;
    }
    public void setDiretores(Set<Diretor> diretores) {
        this.diretores = diretores;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getAno() {
        return ano;
    }
    public void setAno(Integer ano) {
        this.ano = ano;
    }
    public Integer getDuracao() {
        return duracao;
    }
    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }
    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }

    public Map<String, Ator> getElenco() {
        Map<String, Ator> elenco = new HashMap<>();

        atuacoes.forEach(a -> elenco.put(a.getPapel().trim(), a.getId().getAtor()));

        return elenco;
        
    }

    @Override
    public String toString() {
        return String.format("\n\nFilme: %s\nDiretores: %s\nAtuações: %s",
          titulo, diretores, atuacoes);
    }

    
    
    
}
