package fr.hoenheimsports.service.booking.mapper;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.dto.booking.BookingDTO;
import fr.hoenheimsports.repository.booking.entity.booking.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = HallUserMapper.class)
public interface BookingMapper {
    @Mapping(target = "payment.isPaid",source="payment.paid")
    public Booking toBooking(BookingEntity bookingEntity);
    @Mapping(target = "payment.paid",source="payment.isPaid")
    public BookingEntity toBookingEntity(Booking booking);
    public BookingDTO toBookingDTO(Booking booking);

}



