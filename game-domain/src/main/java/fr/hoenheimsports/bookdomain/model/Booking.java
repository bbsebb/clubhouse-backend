package fr.hoenheimsports.bookdomain.model;

import java.util.UUID;

public class Booking {
    private UUID id;
    private Hall hall;
    private HallUser user;
    private Timeslot timeslot;
    private BookingState state;
}
