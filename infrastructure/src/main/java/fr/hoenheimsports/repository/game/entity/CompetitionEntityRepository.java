package fr.hoenheimsports.repository.game.entity;

import fr.hoenheimsports.repository.game.entity.game.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompetitionEntityRepository extends JpaRepository<CompetitionEntity, String> {
}