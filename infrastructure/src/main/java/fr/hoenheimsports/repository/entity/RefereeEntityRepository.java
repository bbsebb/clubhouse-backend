package fr.hoenheimsports.repository.entity;

import fr.hoenheimsports.repository.entity.game.RefereeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefereeEntityRepository extends JpaRepository<RefereeEntity, UUID> {
    public Optional<RefereeEntity> findByName(String name);
}