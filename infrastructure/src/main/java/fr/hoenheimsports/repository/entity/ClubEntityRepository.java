package fr.hoenheimsports.repository.entity;

import fr.hoenheimsports.repository.entity.game.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubEntityRepository extends JpaRepository<ClubEntity, String> {
}