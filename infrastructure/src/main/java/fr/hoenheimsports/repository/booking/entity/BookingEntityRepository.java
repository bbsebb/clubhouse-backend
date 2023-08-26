package fr.hoenheimsports.repository.booking.entity;

import fr.hoenheimsports.repository.booking.entity.booking.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingEntityRepository extends JpaRepository<BookingEntity, UUID> {
    List<BookingEntity> findByTimeslot_StartGreaterThanEqualAndTimeslot_EndLessThanEqual(LocalDateTime start, LocalDateTime end);
    List<BookingEntity> findByTimeslot_StartBetweenAndTimeslot_EndLessThan(LocalDateTime startStart, LocalDateTime startEnd, LocalDateTime end);
}