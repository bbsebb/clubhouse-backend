package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.model.Booking;

import java.util.UUID;

public interface BookingUpdate {

    Booking accept(UUID bookingID);

    Booking refuse(UUID bookingID);

    Booking cancel(UUID bookingID);

    Booking valid(UUID bookingID);
}
