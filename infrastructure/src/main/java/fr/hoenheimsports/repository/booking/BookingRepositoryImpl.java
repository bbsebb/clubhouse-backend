package fr.hoenheimsports.repository.booking;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.Timeslot;
import fr.hoenheimsports.bookdomain.spi.BookingRepository;
import fr.hoenheimsports.repository.booking.entity.BookingEntityRepository;
import fr.hoenheimsports.service.booking.mapper.BookingMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BookingRepositoryImpl implements BookingRepository {
    private final BookingEntityRepository bookingEntityRepository;
    private final BookingMapper bookingMapper;

    public BookingRepositoryImpl(BookingEntityRepository bookingEntityRepository,
                                 BookingMapper bookingMapper) {
        this.bookingEntityRepository = bookingEntityRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public List<Booking> findAll() {
        return this.bookingEntityRepository.findAll().stream().map(bookingMapper::toBooking).toList();
    }

    @Override
    public List<Booking> findByOverlappingTimeslot(Timeslot timeslot) {
        return bookingEntityRepository.findByTimeslot_StartGreaterThanEqualAndTimeslot_EndLessThanEqual(timeslot.start(),timeslot.end())
                        .stream()
                        .map(this.bookingMapper::toBooking)
                        .toList();
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return this.bookingEntityRepository.findById(id).map(bookingMapper::toBooking);
    }

    @Override
    public Booking save(Booking booking) {
        return this.bookingMapper.toBooking(this.bookingEntityRepository.save(this.bookingMapper.toBookingEntity(booking)));
    }
}
