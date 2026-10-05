package br.com.unipds.javify.administrativo.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CartaoCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assinatura_id", nullable = false)
    private Assinatura assinatura;
    @Column(length = 100)
    private String nomeTitular;
    @Column(length = 4)
    private String ultimosQuatroDigitos;
    private String tokenGateway;
    private LocalDate validade;

    public boolean estaVencido() {
        return validade != null && validade.isBefore(LocalDate.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getUltimosQuatroDigitos() {
        return ultimosQuatroDigitos;
    }

    public void setUltimosQuatroDigitos(String ultimosQuatroDigitos) {
        this.ultimosQuatroDigitos = ultimosQuatroDigitos;
    }

    public String getTokenGateway() {
        return tokenGateway;
    }

    public void setTokenGateway(String tokenGateway) {
        this.tokenGateway = tokenGateway;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    @Override
    public String toString() {
        return "CartaoCredito{id=%d, titular='%s', final=%s, validade=%s}"
                .formatted(id, nomeTitular, ultimosQuatroDigitos, validade);
    }
}
