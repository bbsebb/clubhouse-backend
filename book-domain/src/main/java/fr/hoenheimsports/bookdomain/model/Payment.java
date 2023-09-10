package fr.hoenheimsports.bookdomain.model;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Payment class is the main class of the domain. It represents a payment of a booking. It contains the following attributes:
 * @param amount
 * @param isPaid
 * @param paymentType
 * @param collectorId
 */
public record Payment(BigDecimal amount, boolean isPaid, PaymentType paymentType, UUID collectorId) {

    public static final Payment UNKNOWN = new Payment(BigDecimal.ZERO, false, null, null);


    public Payment {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("amount and amountPaid have to be positive");
        if (amount.compareTo(BigDecimal.ZERO) == 0)
            isPaid = true;
    }

    /**
     * Pay the payment
     * @param paymentType the payment type
     * @param collectorId the collector id
     * @return the payment
     */
    public Payment pay(PaymentType paymentType, UUID collectorId) {
        if (paymentType == null || collectorId == null) {
            throw new NullPointerException("paymentType or collectorId should be null");
        }

        return new Payment(this.amount, true, paymentType, collectorId);
    }

}
