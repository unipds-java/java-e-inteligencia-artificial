package br.com.unipds.filmes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "actor")
public class Ator {

    @Id
    @Column(name = "act_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_id_seq")
    @SequenceGenerator(name = "actor_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "act_fname")
    private String nome;

    @Column(name = "act_lname")
    private String sobrenome;

    @Column(name = "act_gender")
    private Character sexo;
    
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
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public Character getSexo() {
        return sexo;
    }
    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getNomeCompleto() {
        return String.format("%s %s", nome.trim(), sobrenome.trim());
    }

    @Override
    public String toString() {
        return getNomeCompleto();
    }

    
}
