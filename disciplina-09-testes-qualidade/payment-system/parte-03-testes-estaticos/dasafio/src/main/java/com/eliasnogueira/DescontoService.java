package com.eliasnogueira;

import java.math.BigDecimal;

public class DescontoService {

    public BigDecimal aplicarDesconto(BigDecimal total) {
        if (total.compareTo(BigDecimal.valueOf(100)) > 0) {
            return total.multiply(BigDecimal.valueOf(0.9));
        }
        return total;
    }
}
