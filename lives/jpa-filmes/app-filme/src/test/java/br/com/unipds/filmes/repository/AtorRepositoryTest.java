package br.com.unipds.filmes.repository;

import br.com.unipds.filmes.model.Ator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
class AtorRepositoryTest {

    @Autowired
    private AtorRepository atorRepository;

    private Ator atorDeNiro;
    private Ator atorWagnerMoura;

    @BeforeEach
    void setUp() {
        // arrange
        atorDeNiro = new Ator();
        atorDeNiro.setNome("Robert");
        atorDeNiro.setSobrenome("De Niro");
        atorDeNiro.setSexo('M');
        atorDeNiro = atorRepository.save(atorDeNiro);

        atorWagnerMoura = new Ator();
        atorWagnerMoura.setNome("Wagner");
        atorWagnerMoura.setSobrenome("Moura");
        atorWagnerMoura.setSexo('M');
        atorWagnerMoura = atorRepository.save(atorWagnerMoura);
    }

    @Test
    void deveEncontrarAtorPorTrechoDeNomeContendoTextoComOrdemAscendente() {
        //act
        List<Ator> atores = atorRepository.findByNomeContainsIgnoreCaseOrderByNomeAsc("rob");

        //assert
        assertThat(atores).hasSize(1);
        assertThat(atores.getFirst().getNome()).isEqualTo("Robert");
    }

}