package fr.hoenheimsports.repository.entity;

import fr.hoenheimsports.repository.entity.game.SeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonEntityRepository extends JpaRepository<SeasonEntity, String> {
}