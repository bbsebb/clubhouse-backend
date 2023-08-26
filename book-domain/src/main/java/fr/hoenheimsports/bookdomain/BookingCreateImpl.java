package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.BookingCreate;
import fr.hoenheimsports.bookdomain.exception.TimeslotAlreadyBooked;
import fr.hoenheimsports.bookdomain.model.*;
import fr.hoenheimsports.bookdomain.rule.*;
import fr.hoenheimsports.bookdomain.spi.BookingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DomainService
public class BookingCreateImpl implements BookingCreate {
    private final BookingRepository bookingRepository;

    public BookingCreateImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking create(Hall hall, HallUser user, Timeslot timeslot,String use) throws TimeslotAlreadyBooked {

        Booking booking = new Booking(UUID.randomUUID(), hall, user, timeslot, BookingState.PENDING, Payment.UNKNOWN, use);

        RuleChain<Booking> bookingRuleChain = RuleChain.buildChain(
                new OverlapPermissionRule(),
                new OverlapRestrictionRule(),
                new TenantBookingStateRule(),
                new AssociationHallUserBookingStateRule(),
                new TenantBookingPaymentRule(),
                new AssociationHallUserBookingPaymentRule());
        booking = bookingRuleChain.handle(booking);

        List<Booking> bookingsWithOverlappingTimeslot = this.bookingRepository.findByOverlappingTimeslot(timeslot);
        if(!booking.hasTimeslotFree(bookingsWithOverlappingTimeslot)) {
            throw new TimeslotAlreadyBooked("timeslot already booked");
        }

        Optional<Booking> userOverlappingBooking = this.findUserOverlappingBookingWithSameStateAndHall(bookingsWithOverlappingTimeslot, user.getId(), booking.getState(), hall);

        if(userOverlappingBooking.isPresent()) {
            Booking olbooking = userOverlappingBooking.get();
            Timeslot mergedTimeslot = olbooking.mergeOverlappingTimeslot(booking.getTimeslot());
            String mergedUse = booking.getUse() + " et " + use;
            booking = new Booking(olbooking.getId(), booking.getHall(), booking.getUser(), mergedTimeslot, booking.getState(), Payment.UNKNOWN, mergedUse);
        }

        return booking;
    }

    @Override
    public Booking save(Booking booking) {
        return this.bookingRepository.save(booking);
    }

    @Override
    public Booking createAndSave(Hall hall, HallUser user, Timeslot timeslot,String use) {
        return this.save(this.create(hall,user,timeslot,use));
    }

    private Optional<Booking> findUserOverlappingBookingWithSameStateAndHall(List<Booking> bookingsWithOverlappingTimeslot, UUID userId, BookingState state, Hall hall) {
        return bookingsWithOverlappingTimeslot.stream()
                .filter(booking -> this.filterSameUserBooking(booking, userId))
                .filter(booking -> booking.getState().equals(state))
                .filter(booking -> booking.getHall().equals(hall))
                .findAny();
    }

    private boolean filterSameUserBooking(Booking booking, UUID userId) {
        return booking.getUser().getId().equals(userId);
    }


}
