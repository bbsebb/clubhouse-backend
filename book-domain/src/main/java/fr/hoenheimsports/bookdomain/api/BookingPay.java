package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.PaymentType;

import java.math.BigDecimal;
import java.util.UUID;

public interface BookingPay {
    Booking pay(UUID id, BigDecimal amountPaid, PaymentType paymentType, UUID collectorId);
}
