package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.exception.TimeslotAlreadyBooked;
import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.bookdomain.model.Hall;
import fr.hoenheimsports.bookdomain.model.Timeslot;

public interface BookingCreate {
    Booking create(Hall halle , HallUser user, Timeslot timeslot) throws TimeslotAlreadyBooked;


}
