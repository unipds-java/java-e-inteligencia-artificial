package br.com.unipds.filmes.telas;

import java.util.List;
import java.util.Scanner;

import br.com.unipds.filmes.model.Ator;
import org.springframework.data.repository.Repository;

import br.com.unipds.filmes.model.Filme;
import br.com.unipds.filmes.repository.FilmeRepository;
import org.springframework.stereotype.Component;

@Component
public class TelaBuscarFilme implements Tela {

    private final FilmeRepository repository;

    public TelaBuscarFilme(FilmeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executar(Scanner entrada) {
        System.out.println("\n**************");
        System.out.println("Busca de filme");
        System.out.println("**************");

        System.out.print("Título a procurar: ");
        String busca = entrada.nextLine();

        List<Filme> resultado = repository.findByTituloContainsIgnoreCaseOrderByTituloAsc(busca);

        if(resultado.isEmpty()) {
            System.out.println("\nNão foram encontrados filmes com o parâmetro informado.");
            return;
        }

        System.out.println("\nResultado da busca:");
        System.out.println("-------------------");
        resultado.forEach(System.out::println);
        System.out.printf("\n%d filme(s) encontrado(s).\n", resultado.size());
    }
    
}