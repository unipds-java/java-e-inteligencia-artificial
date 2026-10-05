package br.com.unipds.filmes.telas;

import java.util.Scanner;

import org.springframework.data.repository.Repository;

import br.com.unipds.filmes.model.Ator;
import br.com.unipds.filmes.repository.AtorRepository;
import org.springframework.stereotype.Component;

@Component
public class TelaCadastrarAtor implements Tela {

    private final AtorRepository repo;

    public TelaCadastrarAtor(AtorRepository repo) {
        this.repo = repo;
    }

    @Override
    public void executar(Scanner entrada) {
        Ator a = new Ator();

        System.out.println("\n*****************");
        System.out.println("Cadastro de Atores");
        System.out.println("*****************");

        System.out.print("Primeiro nome: ");
        a.setNome(entrada.nextLine());
        System.out.print("Sobrenome: ");
        a.setSobrenome(entrada.nextLine());
        System.out.print("Sexo (F/M): ");
        a.setSexo(entrada.next().charAt(0));

        repo.save(a);
        System.out.println("\nAtor cadastrado com sucesso.");
    }
        
}
