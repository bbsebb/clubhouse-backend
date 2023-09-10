package fr.hoenheimsports.dto.booking;

import fr.hoenheimsports.bookdomain.model.PaymentType;

import java.math.BigDecimal;

public record BookingPayDTO( PaymentType paymentType, String collectorId) {
}
