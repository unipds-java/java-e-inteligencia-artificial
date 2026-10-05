package com.eliasnogueira.paymentservice.unit;

import com.eliasnogueira.paymentservice.validator.PaymentLimitValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentValidatorTest {

    @Test
    @DisplayName("Payment should be successful when amount is below the daily limit")
    void shouldAcceptAmountBelowTheLimit() {
        BigDecimal amount = new BigDecimal("1999.99");
        assertThat(PaymentLimitValidator.isWithinLimit(amount)).isTrue();
    }

    @Test
    @DisplayName("Payment should be successful when amount equal the daily limit")
    void shouldAcceptAmountEqualToTheLimit() {
        BigDecimal amount = new BigDecimal("2000.00");
        assertThat(PaymentLimitValidator.isWithinLimit(amount)).isTrue();
    }

    @Test
    @DisplayName("Payment should not be successful when amount is higher than the daily limit")
    void shouldNotAcceptAmountHigherThanTheLimit() {
        BigDecimal amount = new BigDecimal("2000.01");
        assertThat(PaymentLimitValidator.isWithinLimit(amount)).isFalse();
    }

    @Test
    @DisplayName("Payment should be successful when amount is null")
    void shouldNotAcceptANullValue() {
        assertThat(PaymentLimitValidator.isWithinLimit(null)).isFalse();
    }
}
