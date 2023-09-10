package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.PaymentType;

import java.util.UUID;

public interface BookingPay {
    /**
     * Pay a booking
     * @param id
     * @param paymentType
     * @param collectorId
     * @return Booking
     */
    Booking pay(UUID id, PaymentType paymentType, UUID collectorId);
}
