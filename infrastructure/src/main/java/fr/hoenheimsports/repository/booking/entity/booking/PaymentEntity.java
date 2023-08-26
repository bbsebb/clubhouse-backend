package fr.hoenheimsports.repository.booking.entity.booking;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
@Embeddable
public class PaymentEntity {
    private BigDecimal amount;
    private boolean isPaid;
    @Enumerated(EnumType.STRING)
    private PaymentTypeEntity paymentType;
    private UUID collectorId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public PaymentTypeEntity getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypeEntity paymentType) {
        this.paymentType = paymentType;
    }

    public UUID getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(UUID collectorId) {
        this.collectorId = collectorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentEntity that)) return false;
        return isPaid == that.isPaid && Objects.equals(amount, that.amount) && paymentType == that.paymentType && Objects.equals(collectorId, that.collectorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, isPaid, paymentType, collectorId);
    }
}
