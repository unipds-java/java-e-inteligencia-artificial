package br.com.unipds.filmes.repository;

import br.com.unipds.filmes.model.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
class FilmeRepositoryTest {

    private Ator atorRussell;
    private Ator atorWill;

    private Diretor diretorRonHoward;
    private Diretor diretorBarrySonnenfeld;

    private Filme filmeA;
    private Filme filmeB;

    @Autowired
    private AtorRepository atorRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        atorRussell = new Ator();
        atorRussell.setNome("Russell");
        atorRussell.setSobrenome("Crowe");
        atorRussell.setSexo('M');
        atorRussell = atorRepository.save(atorRussell);

        atorWill = new Ator();
        atorWill.setNome("Will");
        atorWill.setSobrenome("Smith");
        atorWill.setSexo('M');
        atorWill = atorRepository.save(atorWill);

        diretorRonHoward = new Diretor();
        diretorRonHoward.setId(1);
        diretorRonHoward.setNome("Ron");
        diretorRonHoward.setSobrenome("Howard");
        entityManager.persist(diretorRonHoward);

        diretorBarrySonnenfeld = new Diretor();
        diretorBarrySonnenfeld.setId(2);
        diretorBarrySonnenfeld.setNome("Barry");
        diretorBarrySonnenfeld.setSobrenome("Sonnenfeld");
        entityManager.persist(diretorBarrySonnenfeld);

        filmeA = new Filme();
        filmeA.setTitulo("A Beautiful Mind");
        filmeA.setAno(2001);
        filmeA.setDuracao(135);
        filmeA.setIdioma("English");
        filmeA.setPais("USA");

        filmeA.getDiretores().add(diretorRonHoward);

        filmeA = filmeRepository.save(filmeA);

        filmeB = new Filme();
        filmeB.setTitulo("Men in Black");
        filmeB.setAno(1997);
        filmeB.setDuracao(98);
        filmeB.setIdioma("English");
        filmeB.setPais("USA");

        filmeB.getDiretores().add(diretorBarrySonnenfeld);

        filmeB = filmeRepository.save(filmeB);

        Atuacao atuacao1 = new Atuacao();
        AtuacaoId atuacaoId1 = new AtuacaoId();
        atuacaoId1.setFilme(filmeA);
        atuacaoId1.setAtor(atorRussell);
        atuacao1.setId(atuacaoId1);
        atuacao1.setPapel("John Nash");
        entityManager.persist(atuacao1);

        Atuacao atuacao2 = new Atuacao();
        AtuacaoId id2 = new AtuacaoId();
        id2.setAtor(atorWill);
        id2.setFilme(filmeB);
        atuacao2.setId(id2);
        atuacao2.setPapel("Agent J");
        entityManager.persist(atuacao2);

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    void deveObterFilmesComDiretoresEAtuacoes() {
        List<Filme> filmesComTudo = filmeRepository.obterFilmes();

        assertThat(filmesComTudo).hasSize(2);

        Filme primeiroFilme = filmesComTudo.getFirst();

        assertThat(primeiroFilme)
                .extracting(Filme::getTitulo)
                .isEqualTo("A Beautiful Mind");

        assertThat(primeiroFilme.getDiretores())
                .extracting(Diretor::getNomeCompleto)
                .containsExactly("Ron Howard");

        assertThat(primeiroFilme.getAtuacoes())
                .extracting(
                        atuacao -> atuacao.getId().getAtor()
                                .getNomeCompleto())
                .containsExactly("Russell Crowe");

        Filme segundoFilme = filmesComTudo.getLast();

        assertThat(segundoFilme)
                .extracting(Filme::getTitulo)
                .isEqualTo("Men in Black");

        assertThat(segundoFilme.getDiretores())
                .extracting(Diretor::getNomeCompleto)
                .containsExactly("Barry Sonnenfeld");

        assertThat(segundoFilme.getAtuacoes())
                .extracting(
                        atuacao -> atuacao.getId().getAtor()
                                .getNomeCompleto())
                .containsExactly("Will Smith");
    }

    @Test
    void deveBuscarFilmesPorTituloIgnorandoCase() {
        List<Filme> filmesComTudo = filmeRepository.findByTituloContainsIgnoreCaseOrderByTituloAsc("men");
        entityManager.clear();

        assertThat(filmesComTudo).hasSize(1);

        assertThat(filmesComTudo)
                .first()
                .extracting(Filme::getTitulo)
                .isEqualTo("Men in Black");

        Filme mib = filmesComTudo.getFirst();
        assertThat(mib.getDiretores())
                .extracting(Diretor::getNomeCompleto)
                .containsExactly("Barry Sonnenfeld");
    }

}