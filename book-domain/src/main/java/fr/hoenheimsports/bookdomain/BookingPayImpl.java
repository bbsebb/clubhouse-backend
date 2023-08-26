package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.BookingPay;
import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.BookingState;
import fr.hoenheimsports.bookdomain.model.PaymentType;
import fr.hoenheimsports.bookdomain.spi.BookingRepository;

import java.math.BigDecimal;
import java.util.UUID;

@DomainService
public class BookingPayImpl implements BookingPay {
    private final BookingRepository bookingRepository;

    public BookingPayImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking pay(UUID id, BigDecimal amountPaid, PaymentType paymentType, UUID collectorId) {
        Booking booking =  this.bookingRepository.findById(id).orElseThrow();
        booking.pay(amountPaid,paymentType,collectorId);
        booking.setState(BookingState.ACCEPTED);
        return this.bookingRepository.save(booking);
    }
}
