package fr.hoenheimsports.bookdomain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Payment(BigDecimal amount,boolean isPaid, PaymentType paymentType, UUID collectorId) {

    public static final Payment UNKNOWN = new Payment(BigDecimal.ZERO,false,null,null);

    public Payment {
        if(amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("amount and amountPaid have to be positive");
        if(amount.compareTo(BigDecimal.ZERO) == 0)
            isPaid = true;
    }

    public Payment pay(BigDecimal amountPaid, PaymentType paymentType, UUID collectorId) {
        if(paymentType == null || collectorId == null) {
            throw new NullPointerException("paymentType or collectorId should be null");
        }
        if(!amountPaid.equals(this.amount)) {
            throw new IllegalArgumentException("the paid amount should be equals than the amout to be paid");
        }
        return new Payment(this.amount,true,paymentType,collectorId);
    }

}
