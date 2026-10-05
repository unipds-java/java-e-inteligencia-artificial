package br.com.unipds.filmes;

import br.com.unipds.filmes.telas.LoopPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjetoFilmesApplication implements CommandLineRunner{
	@Autowired(required = false)
	private LoopPrincipal principal;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoFilmesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (principal != null) {
			principal.loop();
			System.exit(0);
		}
	}
}
