package br.com.unipds.javify.administrativo.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String nome;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    @Column(name = "possui_propagandas", nullable = false)
    private boolean possuiPropagandas;
    @Column(name = "limite_membros", nullable = false)
    private int limiteMembros;
    @Column(name = "modo_offline", nullable = false)
    private boolean modoOffline;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public boolean isPossuiPropagandas() {
        return possuiPropagandas;
    }

    public void setPossuiPropagandas(boolean possuiPropagandas) {
        this.possuiPropagandas = possuiPropagandas;
    }

    public int getLimiteMembros() {
        return limiteMembros;
    }

    public void setLimiteMembros(int limiteMembros) {
        this.limiteMembros = limiteMembros;
    }

    public boolean isModoOffline() {
        return modoOffline;
    }

    public void setModoOffline(boolean modoOffline) {
        this.modoOffline = modoOffline;
    }

    @Override
    public String toString() {
        return "Plano{id=%d, nome='%s', preco=%s}".formatted(id, nome, preco);
    }
}
