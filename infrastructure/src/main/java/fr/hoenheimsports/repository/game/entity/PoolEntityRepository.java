package fr.hoenheimsports.repository.game.entity;

import fr.hoenheimsports.repository.game.entity.game.PoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoolEntityRepository extends JpaRepository<PoolEntity, String> {
}