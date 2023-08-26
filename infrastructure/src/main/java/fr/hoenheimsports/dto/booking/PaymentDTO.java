package fr.hoenheimsports.dto.booking;

import fr.hoenheimsports.bookdomain.model.PaymentType;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentDTO(BigDecimal amount, boolean isPaid, PaymentType paymentType, UUID collectorId) {
}
