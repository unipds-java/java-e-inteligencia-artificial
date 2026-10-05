package com.eliasnogueira;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExemploAnotacoesTest {

    @BeforeAll
    static void preCondicaoGeral() {
        System.out.println("Executou o @BeforeAll");
    }

    @BeforeEach
    void preCondicaoPorTeste() {
        System.out.println("Executou o @BeforeEach");
    }

    @Test
    void algumTeste() {
        System.out.println("Executou o algumTeste()");
    }


    @Test
    void outroTeste() {
        System.out.println("Executou o outroTest()");
    }

    @AfterEach
    void posCondicaoPorTeste() {
        System.out.println("Executou o @AfterEach");
    }

    @AfterAll
    static void posCondicaoGeral() {
        System.out.println("Executou o @AfterAll");
    }
}
