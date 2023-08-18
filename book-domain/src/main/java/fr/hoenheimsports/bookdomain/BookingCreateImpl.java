package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.BookingCreate;
import fr.hoenheimsports.bookdomain.exception.TimeslotAlreadyBooked;
import fr.hoenheimsports.bookdomain.model.*;
import fr.hoenheimsports.bookdomain.rule.CreateBookingStateRule;
import fr.hoenheimsports.bookdomain.spi.BookingRepository;
import fr.hoenheimsports.bookdomain.spi.EmailProvider;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@DomainService
public class BookingCreateImpl implements BookingCreate {
    private final BookingRepository bookingRepository;
    private final EmailProvider emailService;
    private final CreateBookingStateRule createBookingStateRule;

    public BookingCreateImpl(BookingRepository bookingRepository, EmailProvider emailService, CreateBookingStateRule createBookingStateRule) {
        this.bookingRepository = bookingRepository;
        this.emailService = emailService;
        this.createBookingStateRule = createBookingStateRule;
    }

    @Override
    public Booking create(Hall hall, HallUser user, Timeslot timeslot) throws TimeslotAlreadyBooked {
        List<Booking> bookingsWithOverlappingTimeslot = this.bookingRepository.findByOverlappingTimeslot(timeslot);
        if(!this.hasTimeslotFree(user,bookingsWithOverlappingTimeslot)) {
            throw new TimeslotAlreadyBooked("timeslot already booked");
        }

        BookingState bookingState = this.createBookingStateRule.apply(user);

        Optional<Booking> overlappingBooking = this.findUserOverlappingBookingWithSameStateAndHall(bookingsWithOverlappingTimeslot, user.id(), bookingState, hall);
        UUID id = overlappingBooking.map(Booking::id).orElseGet(UUID::randomUUID);;

        Timeslot mergedTimeslot = overlappingBooking
                .map(booking -> this.mergeExistingOverlappingTimeslots(Set.of(booking.timeslot(), timeslot)))
                .orElse(timeslot);

        Booking booking = new Booking(id, hall, user, mergedTimeslot, bookingState);
        booking = this.bookingRepository.save(booking);
        this.emailService.sendEmail(this.emailService.findRecipient(),booking);
        return booking;
    }

    private Optional<Booking> findUserOverlappingBookingWithSameStateAndHall(List<Booking> bookingsWithOverlappingTimeslot, UUID userId, BookingState state, Hall hall) {
        return bookingsWithOverlappingTimeslot.stream()
                .filter(booking -> this.filterSameUserBooking(booking, userId))
                .filter(booking -> booking.state().equals(state))
                .filter(booking -> booking.hall().equals(hall))
                .findAny();
    }

    private boolean hasTimeslotFree(HallUser excludedUser,List<Booking> bookingsToEvaluate) {
        return bookingsToEvaluate.stream()
                .filter(booking -> !this.filterSameUserBooking(booking, excludedUser.id()))
                .filter(this::filterActiveBooking)
                .findAny()
                .isEmpty();
    }

    private Timeslot mergeExistingOverlappingTimeslots(Set<Timeslot>  overlappingTimeslots) {

        var earliestStart = overlappingTimeslots.stream()
                .map(Timeslot::start)
                .min(LocalDateTime::compareTo)
                .orElseThrow();
        var latestEnd = overlappingTimeslots.stream()
                .map(Timeslot::end)
                .max(LocalDateTime::compareTo)
                .orElseThrow();
        return new Timeslot(earliestStart,latestEnd);
    }

    private boolean filterSameUserBooking(Booking booking, UUID userId) {
        return booking.user().id().equals(userId);
    }

    private boolean filterActiveBooking(Booking booking) {
        return booking.state().equals(BookingState.ACCEPTED)
                        || booking.state().equals(BookingState.VALIDATED)
                        || booking.state().equals(BookingState.PENDING)
                        || booking.state().equals(BookingState.FINISHED)
                ;
    }
}
