package fr.hoenheimsports.service.booking;

import fr.hoenheimsports.bookdomain.api.*;
import fr.hoenheimsports.bookdomain.model.Timeslot;
import fr.hoenheimsports.dto.booking.BookingCreateDTO;
import fr.hoenheimsports.dto.booking.BookingDTO;
import fr.hoenheimsports.dto.booking.BookingPayDTO;
import fr.hoenheimsports.service.booking.mapper.BookingMapper;
import fr.hoenheimsports.userdomain.api.UserDisplay;
import fr.hoenheimsports.userdomain.exception.UserNotFoundException;
import fr.hoenheimsports.userdomain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceApplication {
    private final BookingDisplay bookingDisplay;
    private final BookingCreate bookingCreate;
    private final BookingUpdate bookingUpdate;
    private final BookingPay bookingPay;
    private final HallDisplay hallDisplay;
    private final UserDisplay userDisplay;
    private final HallUserServiceApplication userServiceApplication;
    private final BookingMapper bookingMapper;




    public BookingServiceApplication(BookingDisplay bookingDisplay, BookingCreate bookingCreate , BookingUpdate bookingUpdate, BookingPay bookingPay, HallDisplay hallDisplay, UserDisplay userDisplay, HallUserServiceApplication userServiceApplication, BookingMapper bookingMapper) {
        this.bookingDisplay = bookingDisplay;
        this.bookingCreate = bookingCreate;
        this.bookingUpdate = bookingUpdate;
        this.bookingPay = bookingPay;
        this.hallDisplay = hallDisplay;
        this.userDisplay = userDisplay;
        this.userServiceApplication = userServiceApplication;
        this.bookingMapper = bookingMapper;

    }

    public List<BookingDTO> displayBookings() {
        return this.bookingDisplay.findAll().stream().map(this.bookingMapper::toBookingDTO).toList();
    }

    public BookingDTO displayBooking(String id) {
        return this.bookingDisplay.findById(UUID.fromString(id)).map(this.bookingMapper::toBookingDTO).orElseThrow();
    }

    public BookingDTO createBooking(BookingCreateDTO bookingCreateDTO) {
        var user = this.userServiceApplication.createHallUser(bookingCreateDTO.user());
        var hall = this.hallDisplay.findById(UUID.fromString(bookingCreateDTO.halleId())).orElseThrow();
        var timeslot = new Timeslot(bookingCreateDTO.timeslot().start(), bookingCreateDTO.timeslot().end());
        return this.bookingMapper.toBookingDTO(this.bookingCreate.createAndSave(hall,user,timeslot,bookingCreateDTO.use()));
    }

    public BookingDTO acceptBooking(String bookingId) {
        return this.bookingMapper.toBookingDTO(this.bookingUpdate.accept(UUID.fromString(bookingId)));
    }

    public BookingDTO cancelBooking(String bookingId) {
        return this.bookingMapper.toBookingDTO(this.bookingUpdate.cancel(UUID.fromString(bookingId)));
    }

    public BookingDTO refuseBooking(String bookingId) {
        return this.bookingMapper.toBookingDTO(this.bookingUpdate.refuse(UUID.fromString(bookingId)));
    }

    public BookingDTO validBooking(String bookingId) {
        return this.bookingMapper.toBookingDTO(this.bookingUpdate.valid(UUID.fromString(bookingId)));
    }

    public BookingDTO payBooking(String bookingId,BookingPayDTO bookingPayDTO) {
        User collector = this.userDisplay.findById(UUID.fromString(bookingPayDTO.collectorId())).orElseThrow(UserNotFoundException::new);
        return this.bookingMapper.toBookingDTO(this.bookingPay.pay(UUID.fromString(bookingId),bookingPayDTO.paymentType(),collector.getId()));
    }
}
