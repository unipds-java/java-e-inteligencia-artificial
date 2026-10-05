package com.eliasnogueira;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculadoraTest {

    // Happy paths (um assert por teste)
    @Test
    void soma_quando2Mais3_deveRetornar5() {
        assertEquals(5.0, Calculadora.soma(2.0, 3.0));
    }

    @Test
    void soma_quandoMenos1MaisMenos4_deveRetornarMenos5() {
        assertEquals(-5.0, Calculadora.soma(-1.0, -4.0));
    }

    @Test
    void soma_quando0Mais0ponto5_deveRetornar0ponto5() {
        assertEquals(0.5, Calculadora.soma(0.0, 0.5));
    }

    @Test
    void subtracao_quando5Menos3_deveRetornar2() {
        assertEquals(2.0, Calculadora.subtracao(5.0, 3.0));
    }

    @Test
    void subtracao_quandoMenos5MenosMenos3_deveRetornarMenos2() {
        assertEquals(-2.0, Calculadora.subtracao(-5.0, -3.0));
    }

    @Test
    void subtracao_quando0Menos0ponto5_deveRetornarMenos0ponto5() {
        assertEquals(-0.5, Calculadora.subtracao(0.0, 0.5));
    }

    @Test
    void multiplicacao_quando2Vezes3_deveRetornar6() {
        assertEquals(6.0, Calculadora.multiplicacao(2.0, 3.0));
    }

    @Test
    void multiplicacao_quandoMenos2Vezes3_deveRetornarMenos6() {
        assertEquals(-6.0, Calculadora.multiplicacao(-2.0, 3.0));
    }

    @Test
    void multiplicacao_quando2Vezes0_deveRetornar0() {
        assertEquals(0.0, Calculadora.multiplicacao(2.0, 0.0));
    }

    @Test
    void multiplicacao_quando2ponto5Vezes2_deveRetornar5() {
        assertEquals(5.0, Calculadora.multiplicacao(2.5, 2.0));
    }

    @Test
    void divisao_quando6Por3_deveRetornar2() {
        assertEquals(2.0, Calculadora.divisao(6.0, 3.0));
    }

    @Test
    void divisao_quandoMenos6Por3_deveRetornarMenos2() {
        assertEquals(-2.0, Calculadora.divisao(-6.0, 3.0));
    }

    @Test
    void divisao_quando5Por2_deveRetornar2ponto5() {
        assertEquals(2.5, Calculadora.divisao(5.0, 2.0));
    }

    // Decimais com tolerância (um assert por teste)
    @Test
    void soma_quando5ponto2Mais5ponto5_deveRetornar10ponto7_comTolerancia() {
        assertEquals(10.7, Calculadora.soma(5.2, 5.5), 1e-12);
    }

    @Test
    void subtracao_quando5ponto2Menos5ponto5_deveRetornarMenos0ponto3_comTolerancia() {
        assertEquals(-0.3, Calculadora.subtracao(5.2, 5.5), 1e-12);
    }

    // Edge cases (um assert por teste)
    @Test
    void divisao_quandoZeroPorZero_deveRetornarNaN() {
        double resultado = Calculadora.divisao(0.0, 0.0);
        assertTrue(Double.isNaN(resultado), "0.0 / 0.0 deveria ser NaN");
    }

    @Test
    void divisao_quando1PorZeroPositivo_deveRetornarInfinitoPositivo() {
        double resultado = Calculadora.divisao(1.0, 0.0);
        assertTrue(Double.isInfinite(resultado) && resultado > 0, "1.0 / 0.0 deveria ser +Infinity");
    }

    @Test
    void divisao_quando1PorZeroNegativo_deveRetornarInfinitoNegativo() {
        double resultado = Calculadora.divisao(1.0, -0.0);
        assertTrue(Double.isInfinite(resultado) && resultado < 0, "1.0 / -0.0 deveria ser -Infinity");
    }

    // Propagação de NaN (um assert por teste)
    @Test
    void soma_quandoOperandoEsquerdoNaN_deveRetornarNaN() {
        assertTrue(Double.isNaN(Calculadora.soma(Double.NaN, 1.0)));
    }

    @Test
    void soma_quandoOperandoDireitoNaN_deveRetornarNaN() {
        assertTrue(Double.isNaN(Calculadora.soma(1.0, Double.NaN)));
    }

    @Test
    void subtracao_quandoOperandoEsquerdoNaN_deveRetornarNaN() {
        assertTrue(Double.isNaN(Calculadora.subtracao(Double.NaN, 1.0)));
    }

    @Test
    void subtracao_quandoOperandoDireitoNaN_deveRetornarNaN() {
        assertTrue(Double.isNaN(Calculadora.subtracao(1.0, Double.NaN)));
    }

    @Test
    void multiplicacao_quandoOperandoEsquerdoNaN_deveRetornarNaN() {
        assertTrue(Double.isNaN(Calculadora.multiplicacao(Double.NaN, 2.0)));
    }

    @Test
    void multiplicacao_quandoOperandoDireitoNaN_deveRetornarNaN() {
        assertTrue(Double.isNaN(Calculadora.multiplicacao(2.0, Double.NaN)));
    }

    @Test
    void divisao_quandoOperandoEsquerdoNaN_deveRetornarNaN() {
        assertTrue(Double.isNaN(Calculadora.divisao(Double.NaN, 2.0)));
    }

    @Test
    void divisao_quandoOperandoDireitoNaN_deveRetornarNaN() {
        assertTrue(Double.isNaN(Calculadora.divisao(2.0, Double.NaN)));
    }

    // Overflow/Underflow (um assert por teste)
    @Test
    void soma_quandoOverflow_deveRetornarInfinitoPositivo() {
        double resultado = Calculadora.soma(Double.MAX_VALUE, Double.MAX_VALUE);
        assertTrue(Double.isInfinite(resultado) && resultado > 0, "MAX + MAX deveria ser +Infinity");
    }

    @Test
    void multiplicacao_quandoOverflow_deveRetornarInfinitoPositivo() {
        double resultado = Calculadora.multiplicacao(Double.MAX_VALUE, 2.0);
        assertTrue(Double.isInfinite(resultado) && resultado > 0, "MAX * 2 deveria ser +Infinity");
    }

    @Test
    void divisao_quandoUnderflow_deveAproximarParaZero() {
        double resultado = Calculadora.divisao(Double.MIN_VALUE, 2.0);
        assertEquals(0.0, resultado);
    }

    @Test
    void subtracao_quandoMagnitudesExtremas_deveRetornarInfinitoNegativo() {
        double resultado = Calculadora.subtracao(-Double.MAX_VALUE, Double.MAX_VALUE);
        assertTrue(Double.isInfinite(resultado) && resultado < 0, "-MAX - MAX deveria ser -Infinity");
    }
}
