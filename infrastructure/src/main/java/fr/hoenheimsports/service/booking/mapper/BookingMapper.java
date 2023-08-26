package fr.hoenheimsports.service.booking.mapper;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.dto.booking.BookingDTO;
import fr.hoenheimsports.repository.booking.entity.booking.BookingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = HallUserMapper.class)
public interface BookingMapper {
    public Booking toBooking(BookingEntity bookingEntity);
    public BookingEntity toBookingEntity(Booking booking);
    public BookingDTO toBookingDTO(Booking booking);

}



