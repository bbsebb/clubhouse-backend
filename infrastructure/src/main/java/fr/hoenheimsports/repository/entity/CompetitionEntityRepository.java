package fr.hoenheimsports.repository.entity;

import fr.hoenheimsports.repository.entity.game.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompetitionEntityRepository extends JpaRepository<CompetitionEntity, String> {
}