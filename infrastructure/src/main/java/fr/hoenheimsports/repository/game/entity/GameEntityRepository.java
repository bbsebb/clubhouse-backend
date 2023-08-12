package fr.hoenheimsports.repository.game.entity;

import fr.hoenheimsports.repository.game.entity.game.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameEntityRepository extends JpaRepository<GameEntity,String> {
}
