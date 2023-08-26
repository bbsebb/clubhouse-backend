package fr.hoenheimsports.repository.booking.entity;

import fr.hoenheimsports.repository.booking.entity.booking.AssociationHallUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssociationHallUserEntityRepository extends JpaRepository<AssociationHallUserEntity, UUID> {
}