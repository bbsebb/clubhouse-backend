package fr.hoenheimsports.bookdomain.spi.stub;

import fr.hoenheimsports.bookdomain.annotation.Stub;
import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.Timeslot;
import fr.hoenheimsports.bookdomain.spi.BookingRepository;

import java.util.*;
import java.util.stream.Collectors;

@Stub
public class BookingStub implements BookingRepository {

    private final Map<UUID,Booking> bookings;

    public BookingStub(Map<UUID, Booking> bookings) {
        this.bookings = bookings;
    }

    public BookingStub() {
        this(new HashMap<>());
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(this.bookings.values());
    }

    @Override
    public List<Booking> findByOverlappingTimeslot(Timeslot timeslot) {
        Set<Timeslot> timeslots = this.bookings.values().stream().map(Booking::getTimeslot).collect(Collectors.toSet());
        Set<Timeslot> overlappingTimeslots = timeslots.stream()
                .filter(t1 ->  t1.equals(timeslot) || t1.isOverlaps(timeslot))
                .collect(Collectors.toSet());
        return this.bookings.values().stream().filter(booking -> overlappingTimeslots.contains(booking.getTimeslot())).toList();
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return Optional.ofNullable(this.bookings.get(id));
    }

    @Override
    public Booking save(Booking booking) {
         this.bookings.put(booking.getId(),booking);
         return booking;
    }

    public void clear() {
        this.bookings.clear();
    }


}
