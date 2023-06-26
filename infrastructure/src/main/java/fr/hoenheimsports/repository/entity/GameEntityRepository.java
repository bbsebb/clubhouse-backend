package fr.hoenheimsports.repository.entity;

import fr.hoenheimsports.repository.entity.game.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameEntityRepository extends JpaRepository<GameEntity,String> {
}
