package br.com.unipds.filmes.telas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.springframework.data.repository.Repository;

import br.com.unipds.filmes.model.Filme;
import br.com.unipds.filmes.repository.FilmeRepository;
import org.springframework.stereotype.Component;

@Component
public class TelaCadastrarFilme implements Tela {

    private final FilmeRepository repo;

    public TelaCadastrarFilme(FilmeRepository repo) {
        this.repo = repo;
    }

    @Override
    public void executar(Scanner entrada) {
        Filme f = new Filme();

        System.out.println("\n*****************");
        System.out.println("Cadastro de filme");
        System.out.println("*****************");

        System.out.print("Título: ");
        f.setTitulo(entrada.nextLine());
        System.out.print("Idioma: ");
        f.setIdioma(entrada.nextLine());
        System.out.print("País de lançamento (sigla): ");
        f.setPais(entrada.nextLine());
        System.out.print("Data de lançamento: ");
        f.setDataLancamento(LocalDate.parse(entrada.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.print("Ano de produção: ");
        f.setAno(entrada.nextInt());
        System.out.print("Duração (em minutos): ");
        f.setDuracao(entrada.nextInt());

        entrada.nextLine();// Tira o ENTER

        repo.save(f);
        System.out.println("\nFilme cadastrado com sucesso.");
    }
    
}