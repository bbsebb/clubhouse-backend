package fr.hoenheimsports.repository.entity;

import fr.hoenheimsports.repository.entity.game.PoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoolEntityRepository extends JpaRepository<PoolEntity, String> {
}