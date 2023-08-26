package fr.hoenheimsports.repository.booking.entity;

import fr.hoenheimsports.repository.booking.entity.booking.HallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository("bookingHallEntityRepository")
public interface HallEntityRepository extends JpaRepository<HallEntity, UUID> {
}