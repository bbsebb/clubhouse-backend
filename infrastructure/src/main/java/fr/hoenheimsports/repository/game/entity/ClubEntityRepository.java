package fr.hoenheimsports.repository.game.entity;

import fr.hoenheimsports.repository.game.entity.game.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubEntityRepository extends JpaRepository<ClubEntity, String> {
}