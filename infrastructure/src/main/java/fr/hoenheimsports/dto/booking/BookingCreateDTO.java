package fr.hoenheimsports.dto.booking;

public record BookingCreateDTO(String hallId, HallUserCreateDTO user, TimeslotDTO timeslot, String use){

}
