package br.com.unipds.filmes.telas;

import java.util.List;
import java.util.Scanner;
import org.springframework.data.repository.Repository;
import br.com.unipds.filmes.model.Ator;
import br.com.unipds.filmes.repository.AtorRepository;
import org.springframework.stereotype.Component;

@Component
public class TelaListarAtores implements Tela {

    private final AtorRepository repo;

    public TelaListarAtores(AtorRepository repo) {
        this.repo = repo;
    }

    @Override
    public void executar(Scanner entrada) {
        List<Ator> atores = repo.findAll();

        if(atores.isEmpty()) {
            System.out.println("\nNão há nenhum ator cadastrado.");
            return;
        }

        System.out.println("\n***************************");
        System.out.println("Lista de atores cadastrados");
        System.out.println("***************************");
        atores.forEach(System.out::println);
    }
                                  
}
