package fr.hoenheimsports.repository.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByRoleName(String roleName);
}