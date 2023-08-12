package fr.hoenheimsports.repository.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, UUID> {
}