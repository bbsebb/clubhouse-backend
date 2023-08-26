package fr.hoenheimsports.bookdomain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    void constructor_shouldThrowException_whenAmountIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Payment(BigDecimal.valueOf(-1), false, null, null));
    }

    @Test
    void constructor_shouldSetIsPaid_whenAmountIsZero() {
        Payment payment = new Payment(BigDecimal.ZERO, false, null, null);
        assertTrue(payment.isPaid());
    }

    @Test
    void pay_shouldThrowException_whenPaymentTypeIsNull() {
        Payment payment = new Payment(BigDecimal.valueOf(100), false, null, null);
        assertThrows(NullPointerException.class, () -> payment.pay(BigDecimal.valueOf(100), null, UUID.randomUUID()));
    }

    @Test
    void pay_shouldThrowException_whenCollectorIdIsNull() {
        Payment payment = new Payment(BigDecimal.valueOf(100), false, PaymentType.CASH, null);
        assertThrows(NullPointerException.class, () -> payment.pay(BigDecimal.valueOf(100), PaymentType.CASH, null));
    }

    @Test
    void pay_shouldThrowException_whenAmountPaidIsNotEqualToAmount() {
        Payment payment = new Payment(BigDecimal.valueOf(100), false, PaymentType.CASH, UUID.randomUUID());
        assertThrows(IllegalArgumentException.class, () -> payment.pay(BigDecimal.valueOf(50), PaymentType.CASH, UUID.randomUUID()));
    }

    @Test
    void pay_shouldUpdateIsPaidToTrue_whenAmountPaidIsEqualToAmount() {
        Payment payment = new Payment(BigDecimal.valueOf(100), false, PaymentType.CASH, UUID.randomUUID());
        Payment updatedPayment = payment.pay(BigDecimal.valueOf(100), PaymentType.CASH, UUID.randomUUID());
        assertTrue(updatedPayment.isPaid());
    }

    @Test
    void pay_shouldUpdatePaymentTypeAndCollectorId() {
        Payment payment = new Payment(BigDecimal.valueOf(100), false, PaymentType.CASH, UUID.randomUUID());
        UUID newCollectorId = UUID.randomUUID();
        Payment updatedPayment = payment.pay(BigDecimal.valueOf(100), PaymentType.CASH, newCollectorId);

        assertEquals(PaymentType.CASH, updatedPayment.paymentType());
        assertEquals(newCollectorId, updatedPayment.collectorId());
    }

}