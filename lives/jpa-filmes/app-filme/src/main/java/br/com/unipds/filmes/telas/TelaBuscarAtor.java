package br.com.unipds.filmes.telas;

import java.util.List;
import java.util.Scanner;

import br.com.unipds.filmes.model.Ator;
import br.com.unipds.filmes.repository.AtorRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public class TelaBuscarAtor implements Tela {

    private final AtorRepository repo;

    public TelaBuscarAtor(AtorRepository repo) {
        this.repo = repo;
    }

    @Override
    public void executar(Scanner entrada) {
        System.out.println("\n**************");
        System.out.println("Busca de Ator");
        System.out.println("**************");

        System.out.print("Nome a procurar: ");
        String busca = entrada.nextLine();

        //TODO implementar busca por nome case insensitive em ordem crescente
        List<Ator> resultado = repo.findByNomeContainsIgnoreCaseOrderByNomeAsc(busca);

        if(resultado.isEmpty()) {
            System.out.println("\nNão foram encontrados atores com o parâmetro informado.");
            return;
        }

        System.out.println("\nResultado da busca:");
        System.out.println("-------------------");
        resultado.forEach(System.out::println);
        System.out.printf("\n%d Ator(es) encontrado(s).\n", resultado.size());
    }
}
