package fr.hoenheimsports.repository.entity;

import fr.hoenheimsports.repository.entity.game.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, String> {
}