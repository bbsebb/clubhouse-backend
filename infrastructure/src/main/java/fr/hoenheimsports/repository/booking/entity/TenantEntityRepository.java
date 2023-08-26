package fr.hoenheimsports.repository.booking.entity;

import fr.hoenheimsports.repository.booking.entity.booking.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TenantEntityRepository extends JpaRepository<TenantEntity, UUID> {
}