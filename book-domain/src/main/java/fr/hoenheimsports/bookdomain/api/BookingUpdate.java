package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.model.Booking;

public interface BookingUpdate {
    Booking accept(Booking booking);

    Booking refuse(Booking booking);

    Booking cancel(Booking booking);

    Booking valid(Booking booking);
}
