package com.eliasnogueira;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class DescontoServiceTest {
    private final DescontoService descontoService = new DescontoService();

    @Test
    void deveAplicarDescontoQuandoValorForMaiorQue100() {
        // Arrange - prepara o valor que será válido para este cenário
        BigDecimal total = new BigDecimal("150.00");

        // Act - aplica o desconto e retorna o valor total
        BigDecimal result = descontoService.aplicarDesconto(total);

        // Assert - verifica se o desconto foi aplicado ao valor total
        Assertions.assertThat(result).isEqualByComparingTo("135.00");
    }

   @Test
   void naoDeveAplicarDescontoQuandoValorForMenorQue100() {
        BigDecimal total = new BigDecimal("99.99");
        BigDecimal result = descontoService.aplicarDesconto(total);
        Assertions.assertThat(result).isEqualByComparingTo("99.99");
   }
}
