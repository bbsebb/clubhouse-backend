package fr.hoenheimsports.repository.game.entity;

import fr.hoenheimsports.repository.game.entity.game.CoachEntity;

import java.util.Optional;


public interface CoachEntityRepository extends org.springframework.data.jpa.repository.JpaRepository<fr.hoenheimsports.repository.game.entity.game.CoachEntity, java.util.UUID> {
    Optional<CoachEntity> findByName(String name);
}