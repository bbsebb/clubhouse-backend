package fr.hoenheimsports.dto.booking;

import fr.hoenheimsports.bookdomain.model.BookingState;

public record BookingDTO(
        String id,
        HallDTO hall,
        HallUserDTO user,
        String use,
        TimeslotDTO timeslot,
        BookingState state,
        PaymentDTO payment
) {
}
