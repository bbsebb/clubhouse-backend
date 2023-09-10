package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.exception.TimeslotAlreadyBooked;
import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.Hall;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.bookdomain.model.Timeslot;

/**
*   BookingCreate is an interface. It is used to create a booking and save it in the database.
 */
public interface BookingCreate {
    /**
     * Create a booking
     * @param halle
     * @param user
     * @param timeslot
     * @param use
     * @return Booking
     * @throws TimeslotAlreadyBooked
     */
    Booking create(Hall halle, HallUser user, Timeslot timeslot, String use) throws TimeslotAlreadyBooked;

    /**
     * Save a booking in the database
     * @param booking
     * @return Booking
     */
    Booking save(Booking booking);

    /**
     * Create and save a booking in the database
     * @param hall
     * @param user
     * @param timeslot
     * @param use
     * @return Booking
     */
    Booking createAndSave(Hall hall, HallUser user, Timeslot timeslot, String use);
}
