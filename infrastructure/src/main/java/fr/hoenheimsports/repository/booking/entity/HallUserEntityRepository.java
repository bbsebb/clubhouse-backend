package fr.hoenheimsports.repository.booking.entity;

import fr.hoenheimsports.repository.booking.entity.booking.HallUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HallUserEntityRepository extends JpaRepository<HallUserEntity, UUID> {
}