package fr.hoenheimsports.dto.booking;

import fr.hoenheimsports.bookdomain.model.Hall;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.bookdomain.model.Timeslot;
import fr.hoenheimsports.service.booking.BookingServiceApplication;

public record BookingCreateDTO(String halleId , HallUserCreateDTO user, TimeslotDTO timeslot, String use){

}
