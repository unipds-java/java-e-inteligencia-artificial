package com.eliasnogueira.paymentservice.unit;

import com.eliasnogueira.paymentservice.exceptions.PaymentLimitException;
import com.eliasnogueira.paymentservice.validator.PaymentLimitValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @DisplayName("Payment should be successful when amount is zero")
    void shouldNotAcceptZeroValue() {
        assertThatThrownBy(() -> PaymentLimitValidator.isWithinLimit(BigDecimal.ZERO))
                .isInstanceOf(PaymentLimitException.class)
                .hasMessage("Amount must be greater than zero");
    }

    @Test
    @DisplayName("Payment should not be successful when amount is negative")
    void shouldNotAcceptNegativeValue() {
        assertThatThrownBy(() -> PaymentLimitValidator.isWithinLimit(new BigDecimal("-5.00")))
                .isInstanceOf(PaymentLimitException.class)
                .hasMessage("Amount must be greater than zero");
    }
}
