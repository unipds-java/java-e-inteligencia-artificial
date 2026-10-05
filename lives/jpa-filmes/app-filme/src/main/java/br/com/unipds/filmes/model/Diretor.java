package br.com.unipds.filmes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "director")
public class Diretor {

    @Id
    @Column(name = "dir_id")
    private Integer id;

    @Column(name = "dir_fname")
    private String nome;

    @Column(name = "dir_lname")
    private String sobrenome;

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

    public String getNomeCompleto(){
        return String.format("%s %s", nome.trim(), sobrenome.trim());
    }

    @Override
    public String toString() {
        return getNomeCompleto();
    } 
    
}
