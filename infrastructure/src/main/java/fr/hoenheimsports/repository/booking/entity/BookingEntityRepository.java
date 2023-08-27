package fr.hoenheimsports.repository.booking.entity;

import fr.hoenheimsports.repository.booking.entity.booking.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingEntityRepository extends JpaRepository<BookingEntity, UUID> {
    @Query(
            """
            SELECT b
            FROM BookingEntity b
            WHERE NOT (b.timeslot.end < :start OR b.timeslot.start > :end)
            """
    )
    List<BookingEntity> findOverlappingBookings(LocalDateTime start, LocalDateTime end);

}